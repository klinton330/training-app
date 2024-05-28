package com.training.training_app.service;

import com.training.training_app.dto.ReviewDTO;
import com.training.training_app.dto.ReviewDTOResponse;

public interface ReviewService {
	
	public ReviewDTOResponse postReview(Long productId,ReviewDTO reviewDTO);
	public ReviewDTOResponse updateReview(Long productId,Long reviewId,ReviewDTO reviewDTO);
	public void deleteReview(Long productId,Long reviewId);

}
