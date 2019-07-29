package com.mohyehia.bookstore.exceptions;

import org.springframework.http.HttpStatus;

public class CurrentPasswordNotCorrectException extends ApiBaseException {

	private static final long serialVersionUID = 1L;
	
	public CurrentPasswordNotCorrectException(String message) {
		super(message);
	}
	
	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.BAD_REQUEST;
	}

}
