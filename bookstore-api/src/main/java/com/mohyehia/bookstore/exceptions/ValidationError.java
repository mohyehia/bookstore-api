package com.mohyehia.bookstore.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ValidationError {
	private String uri;
	@JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
	private Date timestamp;
	private List<String> errors;
	
	public ValidationError() {
		this.timestamp = new Date();
		this.errors = new ArrayList<>();
	}
	
	public void addError(String error) {
		this.errors.add(error);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
