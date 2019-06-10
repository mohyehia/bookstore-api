package com.mohyehia.bookstore.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mohyehia.bookstore.entities.Book;
import com.mohyehia.bookstore.exceptions.NotFoundException;
import com.mohyehia.bookstore.repositories.BookRepository;
import com.mohyehia.bookstore.services.BookService;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	@MockBean
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;
	
	@TestConfiguration
	static class BookServiceContextConfiguration{
		@Bean
		public BookService bookService() {
			return new BookService();
		}
	}
	
	@Test
	public void testFindAll() {
		// Mockup data
		Book book1 = new Book("New Book", "josh", "Oracle");
		Book book2 = new Book("Another Book", "berlin", "Sun");
		
		List<Book> books = Arrays.asList(book1, book2);
		
		// Given
		given(bookRepository.findAll()).willReturn(books);
		
		// Assertion
		assertThat(bookService.findAll())
			.hasSize(2)
			.contains(book1, book2)
			.doesNotContainNull();
	}
	
	@Test
	public void testFindById() {
		//Mockup data
		Book book = new Book("New Book", "josh", "Oracle");
		book.setId(1L);
		
		//Given
		given(bookRepository.findById(anyLong())).willReturn(Optional.ofNullable(book));
		Book result = bookService.findById(1L);
		
		//Assertion
		assertThat(result.getTitle()).isEqualToIgnoringCase("new book");
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByInvalidId() {
		//Given
		given(bookRepository.findById(anyLong())).willReturn(Optional.empty());
		
		bookService.findById(1L);
	}
	
	@Test
	public void testSaveBook() {
		//Mockup data
		Book book = new Book("New Book", "josh", "Oracle");
		
		//Given
		given(bookRepository.save(Mockito.any(Book.class))).willReturn(book);
		Book createdBook = bookService.save(book);
		
		//Assertion
		assertThat(createdBook.getPublisher()).isEqualTo(book.getPublisher());
	}
	
	@Test
	public void testRemoveBook() {
		//Mockup data
		Book book = new Book("New Book", "josh", "Oracle");
		book.setId(1L);
		
		given(bookRepository.findById(anyLong())).willReturn(Optional.ofNullable(book));
		
		bookService.removeBook(1L);
		
		verify(bookRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	public void testFindByTitle() {
		//Mockup data
		Book book = new Book("New Book", "josh", "Oracle");
		
		//Given
		given(bookRepository.findByTitle(anyString())).willReturn(Optional.ofNullable(book));
		Book result = bookService.findByTitle("new book");
		
		//Assertion
		assertThat(result).isNotNull();
	}
}
