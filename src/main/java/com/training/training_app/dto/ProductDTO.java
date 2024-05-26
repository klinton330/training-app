package com.training.training_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private String productName;
	private double price;
	private Integer quantity;
	private String description;
	private boolean isStockAvailable;

}
