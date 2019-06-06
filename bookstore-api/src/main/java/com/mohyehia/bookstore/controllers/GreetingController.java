package com.mohyehia.bookstore.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GreetingController {
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/greeting")
	public String greeting() {
		return "Welcome to our amazing app!";
	}
}
