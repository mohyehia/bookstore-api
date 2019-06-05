package com.mohyehia.bookstore.entities;

public class JwtResponse {
	private String token;
	
	public JwtResponse() {}

	public JwtResponse(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
