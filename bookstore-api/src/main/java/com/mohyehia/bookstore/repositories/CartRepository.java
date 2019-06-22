package com.mohyehia.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.bookstore.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findByUserId(Long userId);
}
