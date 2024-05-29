package com.training.training_app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.CategoryDTOResponse;
import com.training.training_app.dto.ProductDTO;
import com.training.training_app.dto.ProductDTOResponse;
import com.training.training_app.dto.ReviewDTOResponse;
import com.training.training_app.exception.RecordAlreadyExistException;
import com.training.training_app.exception.ResourceNotFountException;
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
	public ProductDTOResponse postProduct(ProductDTO productdto)  {
		productdto.setStockAvailable(true);
		Product product = changeToProduct(productdto);
		/*
		 * By Native SQL Query Product findProduct =
		 * productRepository.findEntryOfProduct(product.getProductName()); BY JPQL Query
		 * Optional<Product>findProduct =
		 * productRepository.findProduct(product.getProductName());
		 */
		Optional<Product> findProduct = productRepository.findByProductName(product.getProductName());
		if (!findProduct.isPresent()) {
			Product postProduct = productRepository.save(product);
			return changeToProductDTOResponse(postProduct);
		} else
			throw new RecordAlreadyExistException(
					"Product is already found in DB for Product Name:" + productdto.getProductName());

	}

	@Override
	public ProductDTOResponse getById(Long productId){
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ResourceNotFountException("Product is not found DB for Product Id:" + productId));
		return changeToProductDTOResponse(product);
	}

	@Override
	public List<ProductDTOResponse> getAllCategory() {
		return productRepository.findAll().stream().map(this::changeToProductDTOResponse).collect(Collectors.toList());
	}

	@Override
	public ProductDTOResponse updateProduct(Long productId, ProductDTO productdto) {
		if (productdto.getQuantity() < 1)
			productdto.setStockAvailable(false);
		else
			productdto.setStockAvailable(true);
		Product updatedProduct = productRepository.findById(productId).map(x -> {
			x.setProductName(productdto.getProductName());
			x.setPrice(productdto.getPrice());
			x.setDescription(productdto.getDescription());
			x.setQuantity(productdto.getQuantity());
			x.setStockAvailable(productdto.isStockAvailable());
			Category foundcategory = categoryRepository.findByCategoryName(productdto.getCategoryName())
					.orElseThrow(() -> new ResourceNotFountException(
							"Category Name not found in DB Category Name:" + productdto.getCategoryName()));
			x.setCategory(foundcategory);
			return productRepository.save(x);
		}).orElseThrow(() -> new ResourceNotFountException("Product is not found DB for Product Id:" + productId));
		return changeToProductDTOResponse(updatedProduct);
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ResourceNotFountException("Product is not found DB for Product Id:" + productId));
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
				.orElseThrow(() -> new ResourceNotFountException(
						"Category Name not found in DB Category Name:" + productdto.getCategoryName()));
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
		if (product.getReviews() != null) {
			List<ReviewDTOResponse> reviewList = product.getReviews().stream().map(review -> {
				ReviewDTOResponse reviewDTO = new ReviewDTOResponse();
				reviewDTO.setId(review.getId());
				reviewDTO.setName(review.getUser().getName());
				reviewDTO.setEmail(review.getUser().getEmail());
				reviewDTO.setComment(review.getComments());
				return reviewDTO;
			}).collect(Collectors.toList());
			productDTOResponse.setReviews(reviewList);
		}
		return productDTOResponse;

	}

}
