package com.mohyehia.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.UserBilling;
import com.mohyehia.bookstore.entities.UserPayment;
import com.mohyehia.bookstore.services.UserPaymentService;
import com.mohyehia.bookstore.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/payments")
public class UserPaymentController extends BaseController{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPaymentService userPaymentService;
	
	@ApiOperation(value = "get user payments by userId")
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<UserPayment>> findUserPayments() {
		return new ResponseEntity<>(userPaymentService.findByUserId(getCurrentUser().getId()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "save new payment to the user")
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = {"", "/"})
	public ResponseEntity<UserPayment> addPayment(@RequestBody UserPayment userPayment) {		
		UserBilling userBilling = userPayment.getUserBilling();
		userPayment.setUserId(getCurrentUser().getId());
		return new ResponseEntity<>(userPaymentService.save(userPayment, userBilling), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "remove existing payment by its id from database")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removePayment(@PathVariable Long id) {
		userPaymentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "update user default payment")
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> setUserDefaultPayment(@PathVariable Long id) {
		userService.setUserDefaultPayment(getCurrentUser(), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
