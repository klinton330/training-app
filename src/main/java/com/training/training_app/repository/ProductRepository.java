package com.training.training_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.training_app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//Fetch using Native SQL 
	@Query(value="select * from product where product_name=:productName",nativeQuery = true)
	Product findEntryOfProduct(String productName);
	
	//Fetch using JPQL
	@Query("select p from Product p where p.productName=:productName")
	Optional<Product>findProduct(String productName);
	
	////Fetch using Finders Methods
	Optional<Product>findByProductName(String productName);
}
