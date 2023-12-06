package com.orero.libraryapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orero.libraryapp.dto.CategoryDto;
import com.orero.libraryapp.entity.Category;
import com.orero.libraryapp.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;


	@GetMapping("/all")
	public ResponseEntity<Iterable<Category>> findAll(Pageable pageable) {
		return new ResponseEntity<>(categoryService.getAllCategories(pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDto category) {
		return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/save")
	public ResponseEntity<Category> createUser(@Valid @RequestBody Category category) {
		return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
	}

}
