package com.training.training_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.ProductDTO;
import com.training.training_app.model.Product;
import com.training.training_app.repository.ProductRepository;
import com.training.training_app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product postProduct(ProductDTO productdto) {
		Product product = changeToProduct(productdto);
		Product postProduct = productRepository.save(product);
		return postProduct;
	}

	private Product changeToProduct(ProductDTO productdto) {
		Product product = new Product();
		product.setProductName(productdto.getProductName());
		product.setDescription(productdto.getDescription());
		product.setPrice(productdto.getPrice());
		product.setQuantity(productdto.getQuantity());
		product.setStockAvailable(productdto.isStockAvailable());
		return product;
	}

	@Override
	public Product getById(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Id not found"));
	}

	@Override
	public List<Product> getAllCategory() {
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Long productId, ProductDTO productdto) {
		Product updatedProduct = productRepository.findById(productId).map(x -> {
			x.setProductName(productdto.getProductName());
			x.setPrice(productdto.getPrice());
			x.setQuantity(productdto.getQuantity());
			x.setDescription(productdto.getDescription());
			x.setStockAvailable(productdto.isStockAvailable());
			return productRepository.save(x);
		}).orElseThrow(() -> new RuntimeException("User Id not found"));
		return updatedProduct;
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Id not found"));
		if (product != null)
			productRepository.deleteById(productId);

	}

}
