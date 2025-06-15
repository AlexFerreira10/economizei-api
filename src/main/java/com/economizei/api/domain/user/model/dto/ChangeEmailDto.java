package com.economizei.api.domain.user.model.dto;

import jakarta.validation.constraints.NotNull;

public record ChangeEmailDto (
        @NotNull(message = "User ID is required")
        Long userId,
        String newEmail
) {
}
