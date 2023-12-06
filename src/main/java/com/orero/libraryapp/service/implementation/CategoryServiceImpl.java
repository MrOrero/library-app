package com.orero.libraryapp.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orero.libraryapp.dto.CategoryDto;
import com.orero.libraryapp.entity.Category;
import com.orero.libraryapp.exception.EntityNotFoundException;
import com.orero.libraryapp.mappers.CategoryMapper;
import com.orero.libraryapp.repository.CategoryRepository;
import com.orero.libraryapp.service.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return unwrapCategory(category, id);
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> entity = categoryRepository.findById(id);
        Category category = unwrapCategory(entity, id);

        categoryMapper.updateCategoryFromDto(categoryDto, category);

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> entity = categoryRepository.findById(id);
        Category category = unwrapCategory(entity, id);

        categoryRepository.delete(category);
    }


    static Category unwrapCategory(Optional<Category> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Category.class);
    }

}
