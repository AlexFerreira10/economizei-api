package com.economizei.api.domain.category.service;

import com.economizei.api.domain.category.model.Category;
import com.economizei.api.domain.category.model.dto.*;
import com.economizei.api.domain.category.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public DataCategoryDto registerCategory(@Valid RegisterCategoryDto dto) {
        Category category = new Category(dto.name());
        categoryRepository.save(category);
        return new DataCategoryDto(category);
    }

    public DataCategoryDto getCategoryById(Long id) {
        existsById(id);
        return new DataCategoryDto(categoryRepository.getReferenceById(id));
    }

    public Category getCategoryEntityById(Long id) {
        existsById(id);
        return categoryRepository.getReferenceById(id);
    }

    public List<DataCategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(DataCategoryDto::new)
                .toList();
    }

    @Transactional
    public DataCategoryDto updateCategory(@Valid UpdateCategoryDto dto) {
        existsById(dto.id());
        Category category = categoryRepository.getReferenceById(dto.id());
        category.setName(dto.name());
        return new DataCategoryDto(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        existsById(id);
        categoryRepository.deleteById(id);
    }

    private void existsById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
    }
}
