package com.mohyehia.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserShipping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String shippingName;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private boolean defaultShipping;
	
	private Long userId;
	
	public UserShipping() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean isDefaultShipping() {
		return defaultShipping;
	}

	public void setDefaultShipping(boolean defaultShipping) {
		this.defaultShipping = defaultShipping;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}