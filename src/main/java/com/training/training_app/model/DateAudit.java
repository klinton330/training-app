package com.training.training_app.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" },allowGetters = true)
public class DateAudit implements Serializable {

	static final long serialVersionUID = 1L;
	@CreationTimestamp
	@Column(nullable = false,updatable = false)
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;
}
