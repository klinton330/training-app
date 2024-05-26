package com.training.training_app.service;

import java.util.List;

import com.training.training_app.dto.CategoryDTO;
import com.training.training_app.model.Category;

public interface CategoryService {
	
	public Category postCategory(CategoryDTO categorydto);
	public Category getById(Long categoryId);
	public List<Category>getAllCategory();
	public Category updateCategory(Long categoryId, CategoryDTO categorydto);
	public void deleteCategory(Long categoryId);

}
