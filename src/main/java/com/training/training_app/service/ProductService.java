package com.training.training_app.service;

import java.util.List;

import com.training.training_app.dto.ProductDTO;
import com.training.training_app.dto.ProductDTOResponse;
import com.training.training_app.exception.RecordAlreadyExistException;
import com.training.training_app.exception.ResourceNotFountException;
import com.training.training_app.model.Product;

public interface ProductService {
	public ProductDTOResponse postProduct(ProductDTO productdto)throws RecordAlreadyExistException;

	public ProductDTOResponse getById(Long productId) throws ResourceNotFountException;

	public List<ProductDTOResponse> getAllCategory();

	public ProductDTOResponse updateProduct(Long productId, ProductDTO productdto)throws ResourceNotFountException ;

	public void deleteProduct(Long productId);

}
