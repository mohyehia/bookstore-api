package com.mohyehia.bookstore.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.bookstore.entities.Cart;
import com.mohyehia.bookstore.entities.CartItem;
import com.mohyehia.bookstore.exceptions.NotFoundException;
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
		
	public Cart getCartById(Long cartId) {
		try {
			return cartRepository.findById(cartId).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException(String.format("No such cart with id [%d] was found in database.", cartId));
		}
		
	}
	
	public Cart save(Cart cart, List<CartItem> cartItems) {
		cart.setCartItems(cartItems);
		Cart savedCart = cartRepository.save(cart);
		cartItems.forEach(c -> {
			c.setCart(savedCart);
			cartItemRepository.save(c);
		});
		return cart;
	}
}
