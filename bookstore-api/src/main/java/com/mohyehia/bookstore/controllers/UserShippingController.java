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

import com.mohyehia.bookstore.entities.UserShipping;
import com.mohyehia.bookstore.services.UserService;
import com.mohyehia.bookstore.services.UserShippingService;

@RestController
@RequestMapping("/api/v1/shipping")
public class UserShippingController extends BaseController{
	@Autowired
	private UserShippingService userShippingService;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<UserShipping>> findUserShipping(){
		return new ResponseEntity<>(userShippingService.findByUserId(getCurrentUser().getId()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = {"", "/"})
	public ResponseEntity<UserShipping> save(@RequestBody UserShipping userShipping){
		userShipping.setUserId(getCurrentUser().getId());
		return new ResponseEntity<>(userShippingService.save(userShipping), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteShipping(@PathVariable int id){
		userShippingService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> setUserDefaultShipping(@PathVariable int id){
		userService.setUserDefaultShipping(getCurrentUser().getId(), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
