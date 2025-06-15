package com.economizei.api.domain.transaction.model.dto;

import com.economizei.api.domain.category.model.dto.DataCategoryDto;
import com.economizei.api.domain.transaction.model.Transaction;
import com.economizei.api.domain.transaction.model.TransactionType;

import java.time.LocalDateTime;
import java.util.List;

public record DataTransactionDto(
        Long id,
        String description,
        Double amount,
        LocalDateTime dueDate,
        TransactionType type,
        Long statementId,
        List<DataCategoryDto> categories
) {
    public DataTransactionDto(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDueDate(),
                transaction.getType(),
                transaction.getStatement() != null
                        ? transaction.getStatement().getId()
                        : null,
                transaction.getCategories() != null
                        ? transaction.getCategories().stream()
                        .map(DataCategoryDto::new)
                        .toList()
                        : List.of()
        );
    }
}
