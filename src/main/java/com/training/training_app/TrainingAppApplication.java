package com.training.training_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.training.training_app.audit.AuditAwareImpl;

@SpringBootApplication
@EnableJpaAuditing
public class TrainingAppApplication {

	// Swagger URL:http://localhost:8085/swagger-ui/index.html
	public static void main(String[] args) {
		SpringApplication.run(TrainingAppApplication.class, args);
	}
	
	

}
