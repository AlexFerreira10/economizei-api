package com.economizei.api.domain.transaction.service;

import com.economizei.api.domain.bankaccount.service.BankAccountService;
import com.economizei.api.domain.statement.model.Statement;
import com.economizei.api.domain.statement.service.StatementService;
import com.economizei.api.domain.transaction.model.Transaction;
import com.economizei.api.domain.transaction.model.dto.DataTransactionDto;
import com.economizei.api.domain.transaction.model.dto.RegisterTransactionDto;
import com.economizei.api.domain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StatementService statementService;

    @Autowired
    private BankAccountService bankAccountService;

    @Transactional
    public DataTransactionDto registerTransaction(RegisterTransactionDto dto) {
        LocalDate period = LocalDate.now().withDayOfMonth(1);

        Statement statement = statementService.getOrCreateStatement(dto.userId(), period);

        Transaction transaction = new Transaction(
                dto.description(), dto.amount(), dto.type(), statement
        );
        transaction = transactionRepository.save(transaction);

        statement.addTransaction(transaction);
        bankAccountService.getBankAccountByUserId(dto.userId())
                .applyTransaction(transaction);

        return new DataTransactionDto(transaction);
    }
}
