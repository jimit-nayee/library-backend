package com.practice.DTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.web.multipart.MultipartFile;

public class BookDTO {

	private int id;
	private String name;
	private String authorName;
	private String type;
	private String language;
	private int price;
	private MultipartFile bookImg;
	
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
	public MultipartFile getBookImg() {
		return bookImg;
	}
	public void setBookImg(MultipartFile bookImg) {
		this.bookImg = bookImg;
	}
	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", name=" + name + ", authorName=" + authorName + ", type=" + type + ", language="
				+ language + ", price=" + price + ", bookImg=" + bookImg + "]";
	}
	public BookDTO(int id, String name, String authorName, String type, String language, int price,
			MultipartFile bookImg) {
		super();
		this.id = id;
		this.name = name;
		this.authorName = authorName;
		this.type = type;
		this.language = language;
		this.price = price;
		this.bookImg = bookImg;
	}
	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookDTO(String name, String authorName, String type, String language, int price, MultipartFile bookImg) {
		super();
		this.name = name;
		this.authorName = authorName;
		this.type = type;
		this.language = language;
		this.price = price;
		this.bookImg = bookImg;
	}

	
}
