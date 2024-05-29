package com.training.training_app.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.training.training_app.dto.HttpErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

	@ExceptionHandler(ResourceNotFountException.class)
	public ResponseEntity<HttpErrorResponse> resolveException(ResourceNotFountException exception) {
		return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}

	public ResponseEntity<HttpErrorResponse> resolveException(RecordAlreadyExistException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}

	public ResponseEntity<HttpErrorResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		HttpErrorResponse httpResponse = new HttpErrorResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase());
		return new ResponseEntity<>(httpResponse, httpStatus);
	}

}
