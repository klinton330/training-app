package com.training.training_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.training_app.dto.ProductDTO;
import com.training.training_app.dto.ProductDTOResponse;
import com.training.training_app.model.Product;
import com.training.training_app.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductDTOResponse> postProduct(@RequestBody ProductDTO productDTO) {
		System.out.println(productDTO.getProductName());
		return new ResponseEntity<ProductDTOResponse>(productService.postProduct(productDTO), HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<ProductDTOResponse>> getAllProduct() {
		return new ResponseEntity<List<ProductDTOResponse>>(productService.getAllCategory(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductDTOResponse> getProductById(@PathVariable(name = "id") Long productId) {
		return new ResponseEntity<>(productService.getById(productId), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<ProductDTOResponse> getUpdatedProduct(@PathVariable(name = "id") Long productId,
			@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<ProductDTOResponse>(productService.updateProduct(productId, productDTO), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> getDeletedProduct(@PathVariable(name = "id") Long productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.OK);
	}

}
