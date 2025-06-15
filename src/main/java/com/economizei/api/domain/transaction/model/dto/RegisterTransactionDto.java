package com.economizei.api.domain.transaction.model.dto;

import com.economizei.api.domain.category.model.Category;
import com.economizei.api.domain.transaction.model.Transaction;
import com.economizei.api.domain.transaction.model.TransactionType;
import jakarta.validation.constraints.*;

import java.util.List;

public record RegisterTransactionDto(

        @NotNull(message = "User ID is required")
        Long userId,

        @NotBlank(message = "Description must not be blank")
        String description,

        @NotNull(message = "Amount must not be null")
        Double amount,

        @NotNull(message = "Transaction type must not be null")
        TransactionType type,

        List<Long> categoryIds

) {
    public RegisterTransactionDto(Transaction transaction) {
        this(
                transaction.getStatement().getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getCategories()
                        .stream()
                        .map(Category::getId)
                        .toList()
        );
    }
}
