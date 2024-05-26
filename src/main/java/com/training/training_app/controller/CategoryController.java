package com.training.training_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.training_app.dto.CategoryDTO;
import com.training.training_app.model.Category;
import com.training.training_app.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Category> postCategory(@RequestBody CategoryDTO categoryDTO) {
		Category category = categoryService.postCategory(categoryDTO);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory() {
		return new ResponseEntity<List<Category>>(categoryService.getAllCategory(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Category> getById(@PathVariable(name = "id") Long categoryId) {
		return new ResponseEntity<Category>(categoryService.getById(categoryId), HttpStatus.OK);

	}

	@PutMapping("{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(name = "id") Long categoryId,
			@RequestBody CategoryDTO categoryDTO) {
		return new ResponseEntity<Category>(categoryService.updateCategory(categoryId, categoryDTO), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>("Category Deleted Successfully", HttpStatus.OK);
	}

}
