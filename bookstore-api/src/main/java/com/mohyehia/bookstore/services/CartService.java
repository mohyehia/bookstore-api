package com.mohyehia.bookstore.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.bookstore.entities.Cart;
import com.mohyehia.bookstore.entities.CartItem;
import com.mohyehia.bookstore.repositories.CartItemRepository;
import com.mohyehia.bookstore.repositories.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public Cart save(Cart cart, Set<CartItem> cartItems) {
		cart.setCartItems(cartItems);
		Cart createdCart = cartRepository.save(cart);
		cartItems.forEach(c -> cartItemRepository.save(c));
		return createdCart;
	}
}
