package com.training.training_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.training_app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
