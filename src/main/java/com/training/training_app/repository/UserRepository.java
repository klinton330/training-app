package com.training.training_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.training_app.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByName(String user);
	Optional<User> findByEmail(String email);
} 
