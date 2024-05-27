package com.training.training_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.CategoryDTO;
import com.training.training_app.dto.CategoryDTOResponse;
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

	public CategoryDTOResponse changeToCategoryDTO(Category category) {
		CategoryDTOResponse categoryDTOResponse = new CategoryDTOResponse();
		categoryDTOResponse.setId(category.getId());
		categoryDTOResponse.setCategoryName(category.getCategoryName());
		categoryDTOResponse.setProductDTO(categoryDTOResponse.getProductDTO());
		return categoryDTOResponse;
	}

	@Override
	public CategoryDTOResponse postCategory(CategoryDTO categorydto) {
		Category category = categoryRepository.save(changeToCategory(categorydto));
		return changeToCategoryDTO(category);
	}

	@Override
	public CategoryDTOResponse getById(Long categoryId) {
		return changeToCategoryDTO(categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category Id Not found")));
	}

	@Override
	public List<CategoryDTOResponse> getAllCategory() {
		return categoryRepository.findAll().stream().map(this::changeToCategoryDTO).collect(Collectors.toList());
	}

	@Override
	public CategoryDTOResponse updateCategory(Long categoryId, CategoryDTO categorydto) {
		Category updateCategory = categoryRepository.findById(categoryId).map(x -> {
			x=changeToCategory(categorydto);
			return categoryRepository.save(x);
		}).orElseThrow(() -> new RuntimeException("Category Id Not found"));
		return changeToCategoryDTO(updateCategory);

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
