package com.training.training_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.training_app.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTOResponse {
	private long id;
	private String email;
	private String comment;
	private String name; 
	@JsonIgnore
	private Product product; 

}
