package com.economizei.api.domain.bankaccount.service;

import com.economizei.api.domain.bankaccount.model.BankAccount;
import com.economizei.api.domain.bankaccount.model.dto.DataBankAccountDto;
import com.economizei.api.domain.bankaccount.model.dto.RegisterBankAccountDto;
import com.economizei.api.domain.bankaccount.model.dto.UpdateBalanceDto;
import com.economizei.api.domain.bankaccount.repository.BankAccountRepository;
import com.economizei.api.domain.user.model.User;
import com.economizei.api.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public DataBankAccountDto registerBankAccount(RegisterBankAccountDto bankAccountDto) {

        User user = userService.getUserEntityById(bankAccountDto.userId());

        BankAccount bankAccount = new BankAccount(bankAccountDto, user);

        bankAccountRepository.save(bankAccount);

        return new DataBankAccountDto(bankAccount);
    }

    @Transactional
    public DataBankAccountDto updateBalance(UpdateBalanceDto updateBalanceDto) {
        existsById(updateBalanceDto.id());

        BankAccount bankAccount = bankAccountRepository.getReferenceById(updateBalanceDto.id());

        bankAccount.setBalance(updateBalanceDto.newBalance());

        return new DataBankAccountDto(bankAccount);
    }

    @Transactional
    public void disableBankAccount (Long id) {
        existsById(id);

        BankAccount bankAccount  = bankAccountRepository.getReferenceById(id);

        bankAccount.setIsActive(false);
    }

    public List<BankAccount> getBankAccountAll() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountByUserId(Long userId) {
        return bankAccountRepository.findBankAccountByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Not found bank account"));
    }

    private void existsById(Long id) {
        if(!bankAccountRepository.existsById(id)) {
            throw new RuntimeException("Not found bank account");
        }
    }
}
