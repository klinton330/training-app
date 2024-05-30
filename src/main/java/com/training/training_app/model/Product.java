package com.training.training_app.model;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.training_app.audit.AuditAwareImpl;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productName;
	private double price;
	private Integer quantity;
	private String description;
	private boolean isStockAvailable;
	@ManyToOne
	@JoinColumn(name = "cat_id",nullable = false)
	private Category category;
	@OneToMany(mappedBy = "product")
	private List<Review> reviews;
	

}


