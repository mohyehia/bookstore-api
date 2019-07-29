package com.mohyehia.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.entities.ChangePasswordRequest;
import com.mohyehia.bookstore.exceptions.CurrentPasswordNotCorrectException;
import com.mohyehia.bookstore.repositories.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController extends BaseController{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@ApiOperation(value = "Getting user profile")
	@GetMapping(value = {"", "/"})
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ApiUser> profile(){
		return new ResponseEntity<>(getCurrentUser(), HttpStatus.OK);
	}
	
	@PutMapping("/change-password")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest passwordRequest){
		if(!passwordEncoder.matches(passwordRequest.getOldPassword(), getCurrentUser().getPassword()))
			throw new CurrentPasswordNotCorrectException("Current password is not correct");
		getCurrentUser().setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
		userRepository.save(getCurrentUser());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
