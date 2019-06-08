package com.mohyehia.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.bookstore.entities.Book;
import com.mohyehia.bookstore.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll(){
		List<Book> books = bookRepository.findAll();
		
		List<Book> activeBooks = new ArrayList<>();
		for(Book book : books)
			if(book.isActive()) activeBooks.add(book);
		
		return activeBooks;
	}
	
	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public void removeBook(Long id) {
		bookRepository.deleteById(id);
	}
	
	public List<Book> blurrySearch(String keyWord){
		List<Book> books = bookRepository.findByTitleContaining(keyWord);
		
		List<Book> activeBooks = new ArrayList<>();
		for(Book book : books)
			if(book.isActive()) activeBooks.add(book);
		
		return activeBooks;
	}
	
	public Book findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}
}
