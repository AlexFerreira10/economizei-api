package com.economizei.api.domain.user.model.dto;

import jakarta.validation.constraints.NotNull;

public record ChangePasswordDto(
        @NotNull(message = "User ID is required")
        Long userId,
        String newPassword
){
}
