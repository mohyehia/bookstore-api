package com.mohyehia.bookstore.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.Cart;
import com.mohyehia.bookstore.entities.CartItem;
import com.mohyehia.bookstore.services.CartService;

@RestController
@RequestMapping(value = {"/api/v1/cart", "/api/v1/cart/"})
public class CartController extends BaseController{
	@Autowired
	private CartService cartService;
	
	@PostMapping
	public ResponseEntity<Cart> save(@RequestBody Cart cart){
		Set<CartItem> cartItems = cart.getCartItems();
		cart.setUserId(getCurrentUser().getId());
		return new ResponseEntity<>(cartService.save(cart, cartItems), HttpStatus.CREATED);
	}
}
