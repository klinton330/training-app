package com.training.training_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.CategoryDTO;
import com.training.training_app.model.Category;
import com.training.training_app.repository.CategoryRepository;
import com.training.training_app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDTO postCategory(CategoryDTO categorydto) {
		Category category = categoryRepository.save(changeToCategory(categorydto));
		return changeToCategoryDTO(category);
	}

	@Override
	public CategoryDTO getById(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO updateCategory(Long categoryId, Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		// TODO Auto-generated method stub

	}

	public Category changeToCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setCategoryName(categoryDTO.getCategoryName());
		return category;
	}

	public CategoryDTO changeToCategoryDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryName(category.getCategoryName());
		return categoryDTO;
	}

}
