package com.mohyehia.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohyehia.bookstore.entities.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long>{

}
