package com.training.training_app.controller;

import java.util.List;

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
import com.training.training_app.dto.CategoryDTOResponse;
import com.training.training_app.exception.RecordAlreadyExistException;
import com.training.training_app.exception.ResourceNotFountException;
import com.training.training_app.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDTOResponse> postCategory(@RequestBody CategoryDTO categoryDTO)
			throws RecordAlreadyExistException, ResourceNotFountException {
		return new ResponseEntity<>(categoryService.postCategory(categoryDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTOResponse>> getAllCategory() {
		return new ResponseEntity<List<CategoryDTOResponse>>(categoryService.getAllCategory(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<CategoryDTOResponse> getById(@PathVariable(name = "id") Long categoryId)
			throws ResourceNotFountException {
		return new ResponseEntity<CategoryDTOResponse>(categoryService.getById(categoryId), HttpStatus.OK);

	}

	@PutMapping("{id}")
	public ResponseEntity<CategoryDTOResponse> updateCategory(@PathVariable(name = "id") Long categoryId,
			@RequestBody CategoryDTO categoryDTO) throws ResourceNotFountException {
		return new ResponseEntity<CategoryDTOResponse>(categoryService.updateCategory(categoryId, categoryDTO),
				HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long categoryId)
			throws ResourceNotFountException {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>("Category Deleted Successfully", HttpStatus.OK);
	}

}
