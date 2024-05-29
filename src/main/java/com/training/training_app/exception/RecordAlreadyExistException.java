package com.training.training_app.exception;

public class RecordAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordAlreadyExistException(String message) {
		super(message);
	}
}
