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

	@Override
	public Category postCategory(CategoryDTO categorydto) {
		Category category = categoryRepository.save(changeToCategory(categorydto));
		return category;
	}

	@Override
	public Category getById(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Id Not found"));
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Long categoryId, CategoryDTO categorydto) {
		Category updateCategory = categoryRepository.findById(categoryId).map(x -> {
			x.setCategoryName(categorydto.getCategoryName());
			return x;
		}).orElseThrow(() -> new RuntimeException("Category Id Not found"));
		return updateCategory;

	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category getCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category Id Not found"));
		if (getCategory != null) {
			categoryRepository.deleteById(categoryId);
		}

	}

}
