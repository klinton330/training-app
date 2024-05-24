package com.training.training_app.service;

import java.util.List;

import com.training.training_app.dto.CategoryDTO;
import com.training.training_app.model.Category;

public interface CategoryService {
	
	public CategoryDTO postCategory(CategoryDTO categorydto);
	public CategoryDTO getById(Long categoryId);
	public List<CategoryDTO>getAllCategory();
	public CategoryDTO updateCategory(Long categoryId,Category category);
	public void deleteCategory(Long categoryId);

}
