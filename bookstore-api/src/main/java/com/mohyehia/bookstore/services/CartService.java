package com.mohyehia.bookstore.services;

import java.util.List;
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
	
	public List<Cart> findByUserId(Long userId){
		return cartRepository.findByUserId(userId);
	}
		
	public Cart save(Cart cart, Set<CartItem> cartItems) {
		cart = cartRepository.save(cart);
		Long cartId = cart.getId();
		cartItems.forEach(c -> {
			c.setCartId(cartId);
			cartItemRepository.save(c);
		});
		return cart;
	}
}
