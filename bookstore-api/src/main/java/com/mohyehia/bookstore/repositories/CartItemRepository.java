package com.mohyehia.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.bookstore.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByCartId(Long cartId);
}
