package com.training.training_app.service;

import java.util.List;

import com.training.training_app.dto.CategoryDTO;
import com.training.training_app.dto.CategoryDTOResponse;
import com.training.training_app.model.Category;

public interface CategoryService {
	
	public CategoryDTOResponse postCategory(CategoryDTO categorydto);
	public CategoryDTOResponse getById(Long categoryId);
	public List<CategoryDTOResponse>getAllCategory();
	public CategoryDTOResponse updateCategory(Long categoryId, CategoryDTO categorydto);
	public void deleteCategory(Long categoryId);

}
