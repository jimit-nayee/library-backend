package com.practice.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.DTO.BookDTO;
import com.practice.Model.Book;
import com.practice.filter.CustomAuthenticationFilter;
import com.practice.manager.CustomAuthenticationManager;
import com.practice.service.BookService;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	CustomAuthenticationFilter customAuthenticationFilter;

	@MockBean
	CustomAuthenticationManager customAuthenticationManager;

	@InjectMocks
	BookController bookController;

	@MockBean
	BookService bookService;

	private Book book;

	@BeforeEach
	public void setup() throws IOException {
		MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg",
				"Some image data".getBytes());
		book = new Book(5, "ramayan", "valmiki", "ritaul", "gujarati", 499, imageFile.getBytes());
	}

	// Junit test for add book operation
	@Test
	public void givenBookObject_whenAddBook_thenReturnBookObject() throws JsonProcessingException, Exception {

		// given
		given(bookService.addBook(any(Book.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(post("/addBook").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book)));

		// then
		response.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(book.getId())))
				.andExpect(jsonPath("$.name", is(book.getName())))
				.andExpect(jsonPath("$.authorName", is(book.getAuthorName())))
				.andExpect(jsonPath("$.type", is(book.getType())))
				.andExpect(jsonPath("$.language", is(book.getLanguage())))
				.andExpect(jsonPath("$.price", is(book.getPrice())))
				.andExpect(jsonPath("$.bookImg", is(Base64.getEncoder().encodeToString(book.getBookImg()))));
	}

	// Junit test for get all Book operation
	@Test
	public void givenBookObject_whenFindAll_thenReturnBookObject() throws Exception {

		// given
		MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg",
				"Some image data".getBytes());
		List<Book> books = new ArrayList<>();
		Book book1 = new Book(5, "ramayan", "valmiki", "ritaul", "gujarati", 499, imageFile.getBytes());
		Book book2 = new Book(6, "abc", "abc", "love_story", "english", 99, imageFile.getBytes());
		books.add(book1);
		books.add(book2);

		given(bookService.getBookData()).willReturn(books);	

		// when
		ResultActions res = mockMvc.perform(get("/getBookData"));

		// then
		res.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", CoreMatchers.is(books.size())));
	}

	// Junit test for get book data by id operation
	@Test
	public void givenBookObject_whenGetBookById_thenReturnBookObject() throws Exception {

		// given
		int id = 5;
		given(bookService.getBookById(id)).willReturn(Optional.of(book));

		// when
		ResultActions res = mockMvc.perform(get("/getBook/{id}", id));

		// then
		res.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.id", is(book.getId())))
				.andExpect(jsonPath("$.name", is(book.getName())))
				.andExpect(jsonPath("$.authorName", is(book.getAuthorName())))
				.andExpect(jsonPath("$.type", is(book.getType())))
				.andExpect(jsonPath("$.language", is(book.getLanguage())))
				.andExpect(jsonPath("$.price", is(book.getPrice())))
				.andExpect(jsonPath("$.bookImg", is(Base64.getEncoder().encodeToString(book.getBookImg()))));
	}

	// Junit test for delete Book operation
	@Test
	public void givenBookObject_whenDeleteById_thenReturnVerify() throws Exception {

		// given
		int id = 5;
		willDoNothing().given(bookService).deleteBook(id);

		// when
		ResultActions res = mockMvc.perform(delete("/deleteBook/{id}", id));

		// then
		res.andExpect(status().isOk()).andDo(print());
	}

	// Junit test for save book with image operation
	@Test
	public void givenBookObject_whenSaveImg_thenReturnBookWithImg() throws Exception {

		BookDTO bookDto = new BookDTO();
		bookDto.setId(5);
		bookDto.setName("Sample Book");
		bookDto.setAuthorName("John Doe");
		bookDto.setType("Fiction");
		bookDto.setLanguage("English");
		bookDto.setPrice(10);
		bookDto.setBookImg(null);

		// given
		given(bookService.saveBookWithImage(bookDto)).willReturn("Book with image saved successfully..");

		// when
		ResultActions res = mockMvc.perform(post("/saveImg"));

		// then
		res.andExpect(status().isOk());
	}
	
	// Junit test for save book with image operation failure condition
		@Test
		public void givenBookObject_whenSaveImgFailure_thenReturnBookWithImg() throws Exception {

			BookDTO bookDto = new BookDTO();
			bookDto.setId(1);
			bookDto.setName("Sample Book");
			bookDto.setAuthorName("John Doe");
			bookDto.setType("Fiction");
			bookDto.setLanguage("English");
			bookDto.setPrice(10);
			bookDto.setBookImg(null);
			
			// given
			given(bookService.saveBookWithImage(bookDto)).willThrow(new IOException());
			// when
			ResultActions res = mockMvc.perform(post("/saveImg"));

			// then
			res.andExpect(status().isInternalServerError());
			
		}
}