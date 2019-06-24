package com.mohyehia.bookstore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mohyehia.bookstore.entities.Book;
import com.mohyehia.bookstore.services.BookService;
import com.mohyehia.bookstore.utils.ImageUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@ApiOperation(value = "save new book to the database")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = {"", "/"})
	public ResponseEntity<Book> saveBook(@RequestParam("image") MultipartFile file, @Valid @RequestPart Book book) {
		String imageName = ImageUtils.uploadImage(file);
		if(imageName == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
		book.setImage(imageName);
		return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "return all books from database")
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<Book>> getBooks(){
		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "return a book from database by its id")
	@GetMapping("/{id}")
	public ResponseEntity<Book> findBookById(@PathVariable Long id){
		return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "update an existing book by its id")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book){
		book.setId(id);
		return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
	}
	
	@ApiOperation(value = "remove an existing book from database by its id")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id){
		bookService.removeBook(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
