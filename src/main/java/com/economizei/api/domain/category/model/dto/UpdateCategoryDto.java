package com.economizei.api.domain.category.model.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateCategoryDto(
        @NotNull(message = "Category ID is required")
        Long id,
        String name
){
}
