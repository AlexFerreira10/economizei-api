package com.economizei.api.domain.statement.model;

import com.economizei.api.domain.bankaccount.model.BankAccount;
import com.economizei.api.domain.transaction.model.Transaction;
import com.economizei.api.domain.transaction.model.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;

    private LocalDate period;

    @ManyToOne @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transaction.setStatement(this);
        this.transactions.add(transaction);
        recalcTotal();
    }

    public void recalcTotal() {
        this.totalAmount = this.transactions.stream()
                .mapToDouble(t -> t.getType() == TransactionType.INCOME
                        ? t.getAmount()
                        : -t.getAmount())
                .sum();
    }
}