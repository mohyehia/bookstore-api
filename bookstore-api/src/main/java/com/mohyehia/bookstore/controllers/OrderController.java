package com.mohyehia.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohyehia.bookstore.entities.Cart;
import com.mohyehia.bookstore.entities.Order;
import com.mohyehia.bookstore.entities.Payment;
import com.mohyehia.bookstore.entities.Shipping;
import com.mohyehia.bookstore.services.OrderService;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController extends BaseController{
	@Autowired
	private OrderService orderService;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = {"", "/"})
	public ResponseEntity<Order> saveOrder(@RequestBody Order order){
		Shipping shipping = order.getShipping();
		Payment payment = order.getPayment();
		Cart cart = order.getCart();
		order = orderService.saveOrder(order, cart, payment, payment.getBilling(), shipping, getCurrentUser().getId());
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<Order>> getOrders(){
		List<Order> orders = orderService.findByUserId(getCurrentUser().getId());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
		return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
	}
}
