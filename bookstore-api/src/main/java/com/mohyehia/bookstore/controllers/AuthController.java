package com.mohyehia.bookstore.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.entities.JwtResponse;
import com.mohyehia.bookstore.entities.SigninRequest;
import com.mohyehia.bookstore.services.UserService;
import com.mohyehia.bookstore.utils.TokenUtil;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<ApiUser> signup(@Valid @RequestBody ApiUser user){
		ApiUser apiUser = userService.save(user);
		return new ResponseEntity<>(apiUser, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> signIn(@RequestBody SigninRequest signInRequest) {
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
		String token = tokenUtil.generateToken(userDetails);
		return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
	}
}
