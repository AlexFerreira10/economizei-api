package com.economizei.api.domain.category.model.dto;

import com.economizei.api.domain.category.model.Category;

import jakarta.validation.constraints.NotBlank;

public record RegisterCategoryDto(
        @NotBlank(message = "The name field must not be blank")
        String name
){
        public RegisterCategoryDto(Category category) {
                this(category.getName());
        }
}

