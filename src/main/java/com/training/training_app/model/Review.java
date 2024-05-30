package com.training.training_app.model;

import com.training.training_app.audit.AuditAwareImpl;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String comments;
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	@ManyToOne  //Uni directional Mapping
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
}
