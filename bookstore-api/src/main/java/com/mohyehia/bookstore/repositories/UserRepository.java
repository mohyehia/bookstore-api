package com.mohyehia.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.bookstore.entities.ApiUser;

@Repository
public interface UserRepository extends JpaRepository<ApiUser, Long> {
	ApiUser findByEmail(String email);
}
