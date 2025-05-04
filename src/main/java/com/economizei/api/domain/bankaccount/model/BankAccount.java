package com.economizei.api.domain.bankaccount.model;

import com.economizei.api.domain.bankaccount.model.dto.RegisterBankAccountDto;
import com.economizei.api.domain.statement.model.Statement;
import com.economizei.api.domain.transaction.model.Transaction;
import com.economizei.api.domain.transaction.model.TransactionType;
import com.economizei.api.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(unique = true)
    private String accountNumber;

    private String branch;
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Statement> statements = new ArrayList<>();

    public void applyTransaction(Transaction transaction) {
        double delta = transaction.getType() == TransactionType.INCOME
                ? transaction.getAmount()
                : -transaction.getAmount();
        this.balance = (this.balance == null ? 0.0 : this.balance) + delta;
    }

    public BankAccount(RegisterBankAccountDto registerBankAccountDto, User user) {
        this.bankName = registerBankAccountDto.bankName();
        this.balance = registerBankAccountDto.balance();
        this.accountType = registerBankAccountDto.accountType();
        this.accountNumber = registerBankAccountDto.accountNumber();
        this.branch = registerBankAccountDto.branch();
        this.isActive = true;
        this.user = user;
    }
}