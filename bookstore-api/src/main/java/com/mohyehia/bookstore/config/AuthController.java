package com.mohyehia.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.services.UserService;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<ApiUser> signup(@RequestBody ApiUser user){
		return new ResponseEntity<>(userService.save(user, "ROLE_USER"), HttpStatus.CREATED);
	}
}
