package com.economizei.api.category.domain.model;

import com.economizei.api.transaction.domain.model.Transaction;
import com.economizei.api.transaction.domain.model.TransactionType;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TransactionType categoryType;

    @ManyToMany(mappedBy = "categories")
    private List<Transaction> transactions = new ArrayList<>();
}