package com.practice.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.practice.DTO.BookDTO;
import com.practice.Model.Book;
import com.practice.service.BookService;
import com.practice.serviceImplementation.BookServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.OK);
	}

	@GetMapping("/getBookData")
	public List<Book> getBookData() {
		return bookService.getBookData();
	}

	@GetMapping("/getBook/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Book> getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}

	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
		return new ResponseEntity<String>("Book deleted successfully !", HttpStatus.OK);
	}

	@PostMapping("/saveImg")
	public ResponseEntity<String> saveBookWithImage(@ModelAttribute BookDTO bookDto) {
		try {	
			bookService.saveBookWithImage(bookDto);
			return ResponseEntity.ok("Book with image saved successfully.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Failed to save book with image.");
		}
	}
}
