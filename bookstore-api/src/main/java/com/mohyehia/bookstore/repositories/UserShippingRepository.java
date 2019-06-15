package com.mohyehia.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.bookstore.entities.UserShipping;

@Repository
public interface UserShippingRepository extends JpaRepository<UserShipping, Integer>{
	List<UserShipping> findByUserId(Long userId);
}
