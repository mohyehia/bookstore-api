package com.mohyehia.bookstore.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.bookstore.entities.Billing;
import com.mohyehia.bookstore.entities.Cart;
import com.mohyehia.bookstore.entities.Order;
import com.mohyehia.bookstore.entities.Payment;
import com.mohyehia.bookstore.entities.Shipping;
import com.mohyehia.bookstore.exceptions.NotFoundException;
import com.mohyehia.bookstore.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public synchronized Order saveOrder(Order order, Cart cart, Payment payment, Billing billing, Shipping shipping, Long userId) {
		payment.setBilling(billing);
		billing.setPayment(payment);
		order.setPayment(payment);
		order.setShipping(shipping);
		order.setUserId(userId);
		order.setShippingMethod("Credit Card");
		order.setCart(cart);
		order.setOrderTotal(cart.getTotalPrice());
		
		return orderRepository.save(order);
	}
	
	public Order findById(Long id) {
		try {
			return orderRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException(String.format("No such order with id [%d] was found in database.", id));
		}
	}
	
	public List<Order> findByUserId(Long userId){
		return orderRepository.findByUserId(userId);
	}
}
