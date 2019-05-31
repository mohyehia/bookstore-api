package com.mohyehia.bookstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GreetingController {
	@GetMapping("/greeting")
	public String greeting() {
		return "Welcome to our amazing app!";
	}
}
