package com.economizei.api.domain.category.model;

import com.economizei.api.domain.transaction.model.Transaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Transaction> transactions = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }
}
