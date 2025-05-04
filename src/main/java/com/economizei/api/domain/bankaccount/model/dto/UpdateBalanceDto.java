package com.economizei.api.domain.bankaccount.model.dto;

import jakarta.validation.constraints.*;

public record UpdateBalanceDto(

        @NotNull(message = "Account ID is required")
        Long id,

        @NotNull(message = "New balance must not be null")
        @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
        Double newBalance

) { }