package com.economizei.api.domain.bankaccount.model.dto;

import com.economizei.api.domain.bankaccount.model.AccountType;
import com.economizei.api.domain.bankaccount.model.BankAccount;

public record DataBankAccountDto(
        Long id,
        String bankName,
        Double balance,
        AccountType accountType,
        String accountNumber,
        String branch,
        Boolean isActive,
        Long userId
) {
    public DataBankAccountDto(BankAccount bankAccount) {
        this(
                bankAccount.getId(),
                bankAccount.getBankName(),
                bankAccount.getBalance(),
                bankAccount.getAccountType(),
                bankAccount.getAccountNumber(),
                bankAccount.getBranch(),
                bankAccount.getIsActive(),
                bankAccount.getUser() != null ? bankAccount.getUser().getId() : null
        );
    }
}