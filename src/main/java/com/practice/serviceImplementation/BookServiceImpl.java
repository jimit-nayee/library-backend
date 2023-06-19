package com.practice.serviceImplementation;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.practice.DTO.BookDTO;
import com.practice.Model.Book;
import com.practice.Repository.BookRepo;
import com.practice.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private Book book;

	@Override
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
	}

	@Override
	public List<Book> getBookData() {
		return bookRepo.findAll();
	}

	public void saveBookWithImage(BookDTO bookDto) throws IOException {

		int id = bookDto.getId();
		String name = bookDto.getName();
		String authorName = bookDto.getAuthorName();
		String type = bookDto.getType();
		String language = bookDto.getLanguage();
		int price = bookDto.getPrice();
		MultipartFile img = bookDto.getBookImg();
		
		if (img != null) {
			book.setId(id);
			book.setName(name);
			book.setAuthorName(authorName);
			book.setType(type);
			book.setLanguage(language);
			book.setPrice(price);
			book.setBookImg(img.getBytes());
		} 

		bookRepo.save(book);
	}

	@Override
	public Optional<Book> getBookById(int id) {
		return bookRepo.findById(id);
		
		
	}

}