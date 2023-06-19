package com.practice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.DTO.BookDTO;
import com.practice.Model.Book;

public interface BookRepo extends MongoRepository<Book, Integer>{

	void save(BookDTO bookDto);

	
}
