package com.mohyehia.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int qty;
	
	@OneToOne
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	private int price;
	
	private Long cartId;
	
	public CartItem() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public Long getCartId() {
		return cartId;
	}
	
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
}
