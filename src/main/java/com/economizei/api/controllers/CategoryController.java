package com.economizei.api.controllers;

import com.economizei.api.domain.category.model.dto.*;
import com.economizei.api.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Categories", description = "APIs for managing categories")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Register a new category")
    @PostMapping("/register")
    public ResponseEntity<DataCategoryDto> register(
            @RequestBody @Valid RegisterCategoryDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        DataCategoryDto created = categoryService.registerCategory(dto);
        URI uri = uriBuilder
                .path("/categories/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @Operation(summary = "Get category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DataCategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @Operation(summary = "List all categories")
    @GetMapping
    public ResponseEntity<List<DataCategoryDto>> listAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "Update a category")
    @PutMapping("/update")
    public ResponseEntity<DataCategoryDto> update(
            @RequestBody @Valid UpdateCategoryDto dto
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(dto));
    }

    @Operation(summary = "Delete a category")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
