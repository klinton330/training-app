package com.training.training_app.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = { "createdAt", "updatedAt","lastModifiedBy","createdBy" }, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class DateAudit implements Serializable {

	static final long serialVersionUID = 1L;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedAt;
	@LastModifiedBy
	@Column(insertable = false)
	private String lastModifiedBy;
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
}
