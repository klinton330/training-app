package com.training.training_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.training_app.dto.ReviewDTO;
import com.training.training_app.dto.ReviewDTOResponse;
import com.training.training_app.service.ReviewService;

@RestController
@RequestMapping("api/v1/product/{id}/comments")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping
	public ResponseEntity<ReviewDTOResponse> postReview(@PathVariable(name = "id") long productId,
			@RequestBody ReviewDTO reviewDTO) {
		ReviewDTOResponse response = reviewService.postReview(productId, reviewDTO);
		return new ResponseEntity<ReviewDTOResponse>(response, HttpStatus.CREATED);

	}

	@PutMapping("/{reviewid}")
	public ResponseEntity<ReviewDTOResponse> updateReview(@PathVariable(name = "id") long productId,
			@PathVariable(name = "reviewid") long reviewId, @RequestBody ReviewDTO reviewDTO) {
		ReviewDTOResponse response = reviewService.updateReview(productId, reviewId, reviewDTO);
		return new ResponseEntity<ReviewDTOResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{reviewid}")
	public ResponseEntity<String> deleteReview(@PathVariable(name = "id") long productId,
			@PathVariable(name = "reviewid") long reviewId) {
		reviewService.deleteReview(productId, reviewId);
		return new ResponseEntity<String>("Review Deleted Successfully", HttpStatus.NO_CONTENT);
	}
}
