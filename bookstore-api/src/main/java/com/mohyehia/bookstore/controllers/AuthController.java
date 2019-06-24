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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.entities.ConfirmationToken;
import com.mohyehia.bookstore.entities.JwtResponse;
import com.mohyehia.bookstore.entities.SigninRequest;
import com.mohyehia.bookstore.repositories.ConfirmationTokenRepository;
import com.mohyehia.bookstore.services.UserService;
import com.mohyehia.bookstore.utils.MailUtils;
import com.mohyehia.bookstore.utils.TokenUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
	
	private final String confirmAccountUrl = "http://localhost:8080/api/v1/auth/confirm-account/";
	
	@Autowired
	private MailUtils mailUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@ApiOperation(value = "signup as a new user")
	@PostMapping(value = "/signup")
	public ResponseEntity<ApiUser> signup(@Valid @RequestBody ApiUser user){
		ApiUser apiUser = userService.save(user);
		ConfirmationToken token = new ConfirmationToken(apiUser);
		confirmationTokenRepository.save(token);
		String subject = "Complete your registration!";
		String body = "To confirm your account, please click here : "
				+ confirmAccountUrl + token.getToken();
		mailUtils.sendEmail(user.getEmail(), subject, body);
		return new ResponseEntity<>(apiUser, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "login with your email address and password")
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
	
	@ApiOperation(value = "confirm your account after signup")
	@GetMapping("/confirm-account/{token}")
	public ResponseEntity<ApiUser> confirmAccount(@PathVariable String token){
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
		if(confirmationToken != null) {
			ApiUser user = (ApiUser) userService.loadUserByUsername(confirmationToken.getUser().getEmail());
			user.setEnabled(true);
			user = userService.update(user);
			confirmationTokenRepository.deleteById(confirmationToken.getId());
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
}
