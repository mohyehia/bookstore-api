package com.mohyehia.bookstore.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiBaseException{

	private static final long serialVersionUID = 1L;

	public ConflictException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.CONFLICT;
	}

}
