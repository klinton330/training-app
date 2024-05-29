package com.training.training_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.CategoryDTO;
import com.training.training_app.dto.CategoryDTOResponse;
import com.training.training_app.exception.RecordAlreadyExistException;
import com.training.training_app.exception.ResourceNotFountException;
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
		if (!categoryRepository.findByCategoryName(categorydto.getCategoryName()).isPresent()) {
			Category category = categoryRepository.save(changeToCategory(categorydto));
			return changeToCategoryDTO(category);
		}
		throw new RecordAlreadyExistException("Category Name already exist in DB"+categorydto.getCategoryName());
	}

	@Override
	public CategoryDTOResponse getById(Long categoryId)  {
		return changeToCategoryDTO(categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFountException("Category Not found with categoryId Id:"+categoryId)));
	}

	@Override
	public List<CategoryDTOResponse> getAllCategory() {
		return categoryRepository.findAll().stream().map(this::changeToCategoryDTO).collect(Collectors.toList());
	}

	@Override
	public CategoryDTOResponse updateCategory(Long categoryId, CategoryDTO categorydto)  {
		Category updateCategory = categoryRepository.findById(categoryId).map(x -> {
			x.setCategoryName(categorydto.getCategoryName());
			return categoryRepository.save(x);
		}).orElseThrow(() -> new ResourceNotFountException("Category Not found with categoryId Id:"+categoryId));
		return changeToCategoryDTO(updateCategory);

	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category getCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFountException("Category Not found with categoryId Id:"+categoryId));
		if (getCategory != null) {
			categoryRepository.deleteById(categoryId);
		}

	}

}
