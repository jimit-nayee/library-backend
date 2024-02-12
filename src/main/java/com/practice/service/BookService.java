package com.practice.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.practice.DTO.BookDTO;
import com.practice.Model.Book;

public interface BookService {

	public Book addBook(Book book);
	public List<Book> getBookData();
	public String saveBookWithImage(BookDTO bookDto) throws IOException;
	public void deleteBook(int id);
	Optional<Book> getBookById(int id);

}
