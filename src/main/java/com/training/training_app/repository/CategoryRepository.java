package com.training.training_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.training_app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  public Optional<Category> findByCategoryName(String categoryName);
}
