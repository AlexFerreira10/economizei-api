package com.economizei.api.domain.statement.service;

import com.economizei.api.domain.bankaccount.model.BankAccount;
import com.economizei.api.domain.bankaccount.service.BankAccountService;
import com.economizei.api.domain.statement.model.Statement;
import com.economizei.api.domain.statement.repository.StatementRepository;
import com.economizei.api.domain.transaction.model.TransactionType;
import com.economizei.api.domain.transaction.model.dto.DataTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Transactional
    public Statement getOrCreateStatement(Long userId, LocalDate period) {
        BankAccount acct = bankAccountService.getBankAccountByUserId(userId);
        return statementRepository
                .findByBankAccountAndPeriod(acct, period)
                .orElseGet(() -> {
                    Statement statement = new Statement();
                    statement.setBankAccount(acct);
                    statement.setPeriod(period);
                    return statementRepository.save(statement);
                });
    }

    @Scheduled(cron = "0 0 0 1 * *")
    @Transactional
    public void generateMonthlyStatements() {
        LocalDate first = LocalDate.now().withDayOfMonth(1);

        bankAccountService.getBankAccountAll().forEach(bankAccount -> {
            boolean ex = statementRepository
                    .existsByBankAccountAndPeriod(bankAccount, first);
            if (!ex) {
                Statement statement = new Statement();
                statement.setBankAccount(bankAccount);
                statement.setPeriod(first);
                statementRepository.save(statement);
            }
        });
    }

    public List<DataTransactionDto> listIncomeMonthly(Long statementId) {
        Statement statement = getById(statementId);
        return statement.getTransactions().stream()
                .filter(transaction -> transaction.getType()==TransactionType.INCOME)
                .map(DataTransactionDto::new)
                .toList();
    }

    public List<DataTransactionDto> listExpenseMonthly(Long statementId) {
        Statement statement = getById(statementId);
        return statement.getTransactions().stream()
                .filter(transaction -> transaction.getType()==TransactionType.EXPENSE)
                .map(DataTransactionDto::new)
                .toList();
    }

    public Statement getById(Long id) {
        return statementRepository.getReferenceById(id);
    }
}