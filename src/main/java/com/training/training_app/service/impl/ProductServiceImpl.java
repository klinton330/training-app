package com.training.training_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.CategoryDTOResponse;
import com.training.training_app.dto.ProductDTO;
import com.training.training_app.dto.ProductDTOResponse;
import com.training.training_app.model.Category;
import com.training.training_app.model.Product;
import com.training.training_app.repository.CategoryRepository;
import com.training.training_app.repository.ProductRepository;
import com.training.training_app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ProductDTOResponse postProduct(ProductDTO productdto) {
		productdto.setStockAvailable(true);
		Product product = changeToProduct(productdto);
		Product postProduct = productRepository.save(product);
		return changeToProductDTOResponse(postProduct);
	}

	@Override
	public ProductDTOResponse getById(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Id not found"));
		return changeToProductDTOResponse(product);
	}

	@Override
	public List<ProductDTOResponse> getAllCategory() {
		return productRepository.findAll()
                .stream()
                .map(this::changeToProductDTOResponse)
                .collect(Collectors.toList());
	}

	@Override
	public ProductDTOResponse updateProduct(Long productId, ProductDTO productdto) {
		if(productdto.getQuantity()<1)
			productdto.setStockAvailable(false);
		else
			productdto.setStockAvailable(true);
		Product updatedProduct = productRepository.findById(productId).map(x -> {
			x = changeToProduct(productdto);
			return productRepository.save(x);
		}).orElseThrow(() -> new RuntimeException("User Id not found"));
		return changeToProductDTOResponse(updatedProduct);
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Id not found"));
		if (product != null)
			productRepository.deleteById(productId);

	}

	private Product changeToProduct(ProductDTO productdto) {
		Product product = new Product();
		product.setProductName(productdto.getProductName());
		product.setDescription(productdto.getDescription());
		product.setPrice(productdto.getPrice());
		product.setQuantity(productdto.getQuantity());
		product.setStockAvailable(productdto.isStockAvailable());
		Category foundcategory = categoryRepository.findByCategoryName(productdto.getCategoryName())
				.orElseThrow(() -> new RuntimeException("Category not found in thie name"));
		product.setCategory(foundcategory);
		return product;
	}

	private ProductDTOResponse changeToProductDTOResponse(Product product) {
		ProductDTOResponse productDTOResponse = new ProductDTOResponse();
		productDTOResponse.setId(product.getId());
		productDTOResponse.setDescription(product.getDescription());
		productDTOResponse.setPrice(product.getPrice());
		productDTOResponse.setProductName(product.getProductName());
		productDTOResponse.setQuantity(product.getQuantity());
		productDTOResponse.setStockAvailable(product.isStockAvailable());
		CategoryDTOResponse categoryDTOResponse = new CategoryDTOResponse();
		categoryDTOResponse.setId(product.getCategory().getId());
		categoryDTOResponse.setCategoryName(product.getCategory().getCategoryName());
		productDTOResponse.setCategory(categoryDTOResponse);
		return productDTOResponse;

	}

}
