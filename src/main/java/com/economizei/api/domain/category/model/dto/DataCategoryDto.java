package com.economizei.api.domain.category.model.dto;

import com.economizei.api.domain.category.model.Category;

public record DataCategoryDto(
        Long id,
        String name

) {
    public DataCategoryDto(Category category) {
        this(
                category.getId(),
                category.getName()
        );
    }
}
