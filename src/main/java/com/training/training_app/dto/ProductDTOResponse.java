package com.training.training_app.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOResponse {
	private Long id;
	private String productName;
	private double price;
	private Integer quantity;
	private String description;
	private boolean isStockAvailable;
	private CategoryDTOResponse category;
	private List<ReviewDTOResponse>reviews;
}
