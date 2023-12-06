package com.orero.libraryapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.orero.libraryapp.dto.CategoryDto;
import com.orero.libraryapp.entity.Category;

public interface CategoryService {
    Page<Category> getAllCategories(Pageable pageable);

    Category getCategory(Long id);

    Category saveCategory(Category category);

    Category updateCategory(Long id, CategoryDto category);

    void deleteCategory(Long id);
}