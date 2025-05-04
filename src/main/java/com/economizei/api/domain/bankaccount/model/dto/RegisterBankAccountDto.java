package com.economizei.api.domain.bankaccount.model.dto;

import com.economizei.api.domain.bankaccount.model.AccountType;
import com.economizei.api.domain.bankaccount.model.BankAccount;
import jakarta.validation.constraints.*;

public record RegisterBankAccountDto(

        @NotBlank(message = "Bank name must not be blank")
        String bankName,

        @NotNull(message = "Balance must not be null")
        @DecimalMin(value = "0.00", inclusive = true, message = "Balance cannot be negative")
        Double balance,

        @NotNull(message = "Account type must not be null")
        AccountType accountType,

        @NotBlank(message = "Account number must not be blank")
        String accountNumber,

        @NotBlank(message = "Branch must not be blank")
        String branch,

        @NotNull(message = "User ID is required")
        Long userId

) {
    public RegisterBankAccountDto(BankAccount bankAccount) {
        this(
                bankAccount.getBankName(),
                bankAccount.getBalance(),
                bankAccount.getAccountType(),
                bankAccount.getAccountNumber(),
                bankAccount.getBranch(),
                bankAccount.getUser() != null ? bankAccount.getUser().getId() : null
        );
    }
}