package com.economizei.api.bankaccount.domain.model;

import com.economizei.api.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String accountNumber;
    private String branch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}