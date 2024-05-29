package com.training.training_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.ReviewDTO;
import com.training.training_app.dto.ReviewDTOResponse;
import com.training.training_app.exception.ResourceNotFountException;
import com.training.training_app.model.Product;
import com.training.training_app.model.Review;
import com.training.training_app.model.User;
import com.training.training_app.repository.ProductRepository;
import com.training.training_app.repository.ReviewRepository;
import com.training.training_app.repository.UserRepository;
import com.training.training_app.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ReviewDTOResponse postReview(Long productId, ReviewDTO reviewDTO) {
		Review review = new Review();
		review.setComments(reviewDTO.getComments());
		User user = userRepository.findByName(reviewDTO.getUsername())
				.orElseThrow(() -> new ResourceNotFountException("User is not found DB for user Id:" + reviewDTO.getUsername()));
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFountException("Product is not found DB for Product Id:" + productId));
		review.setProduct(product);
		review.setUser(user);
		Review postedReview = reviewRepository.save(review);
		return changeToReviewDTO(postedReview);
	}

	@Override
	public ReviewDTOResponse updateReview(Long productId, Long reviewId, ReviewDTO reviewDTO) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFountException("Product is not found DB for Product Id:" + productId));
		Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFountException("Review not found DB for Review Id:" + reviewId));
		if (review.getProduct().getId() != product.getId())
			throw new RuntimeException("Review Product id and Product Id not same");
		if (review.getUser().getName().equalsIgnoreCase(reviewDTO.getUsername())) {
			review.setComments(reviewDTO.getComments());
		} else
			throw new RuntimeException("Review user are not equal with reviewDTO user");
		Review postedReview = reviewRepository.save(review);
		return changeToReviewDTO(postedReview);
	}

	public ReviewDTOResponse changeToReviewDTO(Review review) {
		ReviewDTOResponse reviewDTO = new ReviewDTOResponse();
		reviewDTO.setId(review.getId());
		reviewDTO.setName(review.getUser().getName());
		reviewDTO.setEmail(review.getUser().getEmail());
		reviewDTO.setComment(review.getComments());
		reviewDTO.setProduct(review.getProduct());
		return reviewDTO;
	}

	@Override
	public void deleteReview(Long productId, Long reviewId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()->new ResourceNotFountException("Product is not found DB for Product Id:" + productId));
		Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFountException("Review not found DB for Review Id:" + productId));
		if (review.getProduct().getId() != product.getId())
			throw new RuntimeException("Review Product id and Product Id not same");
		reviewRepository.deleteById(reviewId);

	}

}
