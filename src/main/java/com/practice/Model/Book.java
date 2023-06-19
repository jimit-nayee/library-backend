package com.practice.Model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document
@Component
public class Book {

	@Id
	@Indexed(unique = true)
	private int id;
	private String name;
	private String authorName;
	private String type;
	private String language;
	private int price;
	private byte[] bookImg;

	public byte[] getBookImg() {
		return bookImg;
	}

	public void setBookImg(byte[] bookImg) {
		this.bookImg = bookImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", authorName=" + authorName + ", type=" + type + ", language="
				+ language + ", price=" + price + ", bookImg=" + Arrays.toString(bookImg) + "]";
	}

	

	public Book(String name, String authorName, String type, String language, int price, byte[] bookImg) {
		super();
		this.name = name;
		this.authorName = authorName;
		this.type = type;
		this.language = language;
		this.price = price;
		this.bookImg = bookImg;
	}

	public Book(int id, String name, String authorName, String type, String language, int price, byte[] bookImg) {
		super();
		this.id = id;
		this.name = name;
		this.authorName = authorName;
		this.type = type;
		this.language = language;
		this.price = price;
		this.bookImg = bookImg;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
