package com.training.training_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.training_app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
