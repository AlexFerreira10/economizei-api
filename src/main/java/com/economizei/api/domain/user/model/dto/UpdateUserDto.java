package com.economizei.api.domain.user.model.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDto(
        @NotNull(message = "User ID is required")
        Long id,
        String name,
        String phone,
        String email,
        String password
) {
}