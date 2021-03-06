package com.mohyehia.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.Cart;
import com.mohyehia.bookstore.entities.CartItem;
import com.mohyehia.bookstore.services.CartService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = {"/api/v1/cart", "/api/v1/cart/"})
public class CartController extends BaseController{
	@Autowired
	private CartService cartService;
		
	@ApiOperation(value = "return shopping cart for a single user by his id")
	@GetMapping("")
	public ResponseEntity<List<Cart>> getAll(){
		return new ResponseEntity<>(cartService.findByUserId(getCurrentUser().getId()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "get cart details by cartId")
	@GetMapping("/{cartId}")
	public ResponseEntity<Cart> getCartItems(@PathVariable Long cartId){
		return new ResponseEntity<> (cartService.getCartById(cartId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "save new cart for the user")
	@PostMapping("")
	public ResponseEntity<Cart> saveCart(@RequestBody Cart cart){
		List<CartItem> cartItems = cart.getCartItems();
		cart.setUserId(getCurrentUser().getId());
		return new ResponseEntity<>(cartService.save(cart, cartItems), HttpStatus.CREATED);
	}
}
