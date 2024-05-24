package com.training.training_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.training_app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
