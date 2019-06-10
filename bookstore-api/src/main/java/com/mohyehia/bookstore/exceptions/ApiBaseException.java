package com.mohyehia.bookstore.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ApiBaseException(String message) {
		super(message);
	}
	
	public abstract HttpStatus getStatusCode();

}
