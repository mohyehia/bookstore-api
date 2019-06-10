package com.mohyehia.bookstore.exceptions;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ApiBaseException.class)
	public ResponseEntity<ErrorDetails> handleApiExceptions(ApiBaseException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, ex.getStatusCode());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ValidationError validationError = new ValidationError();
		validationError.setUri(request.getDescription(false));
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		for(FieldError error : fieldErrors)
			validationError.addError(error.getDefaultMessage());
		
		return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
	}
	
}
