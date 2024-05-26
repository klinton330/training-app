package com.training.training_app.service;

import java.util.List;

import com.training.training_app.dto.ProductDTO;
import com.training.training_app.model.Product;

public interface ProductService {
	public Product postProduct(ProductDTO productdto);

	public Product getById(Long productId);

	public List<Product> getAllCategory();

	public Product updateProduct(Long productId, ProductDTO productdto);

	public void deleteProduct(Long productId);

}
