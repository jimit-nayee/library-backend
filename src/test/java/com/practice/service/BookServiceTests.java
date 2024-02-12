package com.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import com.practice.DTO.BookDTO;
import com.practice.Model.Book;
import com.practice.Repository.BookRepo;
import com.practice.serviceImplementation.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

	@Mock
	private BookRepo bookRepo;

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	private Book book;
	
	private BookDTO bookDTO;

	@BeforeEach
	public void setup() throws IOException {
		MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "Some image data".getBytes());
        book = new Book(5, "ramayan", "valmiki", "ritaul", "gujarati", 499, imageFile.getBytes());
		bookDTO = new BookDTO(5, "ramayan", "valmiki", "ritaul", "gujarati", 499, imageFile);
	}

	// Junit test for add new book operation
	@Test
	public void givenBookObject_whenSaveBook_thenReturnBookObject() {

		// given
		BDDMockito.given(bookRepo.save(book)).willReturn(book);

		// when
		Book addBook = bookServiceImpl.addBook(book);

		// then
		assertThat(addBook).isNotNull();
	}

	// Junit test for get all book data operation
	@Test
	public void givenBookObject_whenFindAllBook_thenReturnBookObject() {

		// given
		BDDMockito.given(bookRepo.findAll()).willReturn(List.of(book));

		// when
		List<Book> bookList = bookServiceImpl.getBookData();

		// then
		assertThat(bookList).isNotNull();
		assertThat(bookList.size());
	}

	// Junit test for find book by id operation
	@Test
	public void givenBookObject_whenFindById_thenReturnBookObject() {

		// given
		BDDMockito.given(bookRepo.findById(book.getId())).willReturn(Optional.empty());

		// when
		Optional<Book> bookById = bookServiceImpl.getBookById(book.getId());

		// then
		assertThat(bookById).isNotNull();
	}

	// Junit test for delete book data operation
	@Test
	public void givenBookObject_whenDeleteBook_thenReturnVerify() {

		// given
		BDDMockito.willDoNothing().given(bookRepo).deleteById(book.getId());

		
		bookServiceImpl.deleteBook(book.getId());

		// then
		verify(bookRepo, times(1)).deleteById(book.getId());
	}
	
	
	// Junit test for add book with image operation
	@Test
	public void testSaveBookWithImage() throws IOException {
		
	    BookDTO bookDto = new BookDTO();
	    bookDto.setId(1);
	    bookDto.setName("Sample Book");
	    bookDto.setAuthorName("John Doe");
	    bookDto.setType("Fiction");
	    bookDto.setLanguage("English");
	    bookDto.setPrice(10);	    
	    bookDto.setBookImg(null);

	    // Mock the behavior of the bookRepo.save() method
	    Book savedBook = new Book();
	    savedBook.setId(1);
	    savedBook.setName("Sample Book");
	    savedBook.setAuthorName("John Doe");
	    savedBook.setType("Fiction");
	    savedBook.setLanguage("English");
	    savedBook.setPrice(10);
	    
	    Mockito.when(bookRepo.save(Mockito.any(Book.class))).thenReturn(savedBook);

	    // Call the service method
	    String result = bookServiceImpl.saveBookWithImage(bookDto);

	    // Verify that the bookRepo.save() method was called with the correct arguments
	    Mockito.verify(bookRepo, Mockito.times(1)).save(Mockito.any(Book.class));

	    // Assert the expected result
	    assertEquals("book save successfully!!", result);
	}


	
//	//Junit test for .... operation
//	@Test
//	public void givenBookImage_whenSaveImage_thenReturnWithImage() throws IOException {
//		
//		//given
//		BDDMockito.given(bookRepo.save(book)).willReturn(book);
//		
//		//when
//		String bookImg = bookServiceImpl.saveBookWithImage(bookDTO);
//		
//		//then
//		assertThat(bookImg).isNotNull();
//	}
	
//	@Test
//	public void test_saveBookWithImage() throws IOException {
//		
//		when(bookRepo.save(book)).thenReturn(book);
//		
//		assertThat(bookServiceImpl.saveBookWithImage(bookDTO)).isEqualTo("book save successfully!!");
//	}
	
}
