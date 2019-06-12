package com.mohyehia.bookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.bookstore.entities.Book;
import com.mohyehia.bookstore.exceptions.ConflictException;
import com.mohyehia.bookstore.exceptions.NotFoundException;
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
		try {
			return bookRepository.findById(id).get();			
		} catch (NoSuchElementException e) {
			throw new NotFoundException(String.format("No such record with id [%d] was found in database.", id));
		}
	}
	
	public Book save(Book book) {
		if(exists(book.getTitle()))
			throw new ConflictException(String.format("An existing book with the same title [%s] was found in database.", book.getTitle()));
		return bookRepository.save(book);
	}
	
	public void removeBook(Long id) {
		try {
			bookRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException(String.format("No such record with id [%d] was found in database.", id));
		}
	}
	
	public List<Book> blurrySearch(String keyWord){
		List<Book> books = bookRepository.findByTitleContaining(keyWord);
		
		List<Book> activeBooks = new ArrayList<>();
		for(Book book : books)
			if(book.isActive()) activeBooks.add(book);
		
		return activeBooks;
	}
	
	public Book updateBook(Book book) {
		try {
			bookRepository.findById(book.getId()).get();
			return bookRepository.save(book);
		} catch (NoSuchElementException e) {
			throw new NotFoundException(String.format("No such record with id [%d] was found in database.", book.getId()));
		}
	}
	
	public Book findByTitle(String title) {
		return bookRepository.findByTitle(title).get();
	}
	
	private boolean exists(String title) {
		return bookRepository.findByTitle(title).isPresent();
	}
}
