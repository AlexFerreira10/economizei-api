package com.economizei.api.domain.statement.model.dto;

import com.economizei.api.domain.statement.model.Statement;
import com.economizei.api.domain.transaction.model.dto.DataTransactionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DataStatementDto(
        Long id,
        Double totalAmount,
        LocalDate period,
        Long userId,
        List<DataTransactionDto> transactions
) {
    public DataStatementDto(Statement statement) {
        this(
                statement.getId(),
                statement.getTotalAmount(),
                statement.getPeriod(),
                statement.getBankAccount().getUser().getId() != null ? statement.getBankAccount().getUser().getId() : null,
                statement.getTransactions().stream()
                        .map(DataTransactionDto::new)
                        .collect(Collectors.toList())
        );
    }
}