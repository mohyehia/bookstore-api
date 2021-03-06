package com.mohyehia.bookstore.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.bookstore.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContaining(String keyWord);
	
	Optional<Book> findByTitle(String title);
}
