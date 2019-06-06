package com.mohyehia.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.bookstore.entities.ApiUser;

@Repository
public interface UserRepository extends JpaRepository<ApiUser, Long> {
	Optional<ApiUser> findByEmail(String email);
}
