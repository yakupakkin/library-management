package com.library.management.exception;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8346782228042061919L;

	public BookNotFoundException(String message) {
		super(message);
	}
}
