package com.economizei.api.domain.transaction.model;

import com.economizei.api.domain.category.model.Category;
import com.economizei.api.domain.statement.model.Statement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double amount;
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "statement_id", nullable = false)
    private Statement statement;

    @ManyToMany
    @JoinTable(
            name = "transaction_category",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Transaction(String description, Double amount, TransactionType type, Statement statement) {
        this.description = description;
        this.amount = amount;
        this.dueDate = LocalDateTime.now();
        this.type = type;
        this.statement = statement;
    }
}