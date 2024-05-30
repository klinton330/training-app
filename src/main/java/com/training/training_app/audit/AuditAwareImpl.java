package com.training.training_app.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
@Component
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		System.out.println("inside get");
		return Optional.of("SYSTEM");
	}

}
