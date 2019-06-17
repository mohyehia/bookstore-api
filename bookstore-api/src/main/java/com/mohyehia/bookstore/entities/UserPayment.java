package com.mohyehia.bookstore.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PAYMENT")
public class UserPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TYPE")
	private String type;

	@Column(name = "CARD_NAME")
	private String cardName;
	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "EXPIRATION_MONTH")
	private int expirationMonth;
	
	@Column(name = "EXPIRATION_YEAR")
	private int expirationYear;
	
	@Column(name = "CVC")
	private int cvc;
	
	@Column(name = "HOLDER_NAME")
	private String holderName;
	
	@Column(name = "DEFAULT_PAYMENT")
	private boolean defaultPayment;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment")
	private UserBilling userBilling;
	
	public UserPayment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public boolean isDefaultPayment() {
		return defaultPayment;
	}

	public void setDefaultPayment(boolean defaultPayment) {
		this.defaultPayment = defaultPayment;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserBilling getUserBilling() {
		return userBilling;
	}

	public void setUserBilling(UserBilling userBilling) {
		this.userBilling = userBilling;
	}
	
}
