package com.example.book_system.vo;

import java.util.List;
import java.util.Optional;

import com.example.book_system.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

	private Book book;

	@JsonProperty("book_list")
	private List<Book> bookList;
	// 回傳訊息
	private String message;
	// 書名
	private String name;
	// 書籍編碼
	private String isbn;
	// 作者
	private String author;
	// 價格
	private Integer price;
	// 庫存量
	private Integer inventory;

	private List<BookResponse> resList;

	public BookResponse() {

	}

	public BookResponse(List<Book> bookList, String message) {
		super();
		this.bookList = bookList;
		this.message = message;
	}

	public BookResponse(String name, String isbn, String author, Integer price, Integer inventory) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.inventory = inventory;
	}

	public BookResponse(String name, String isbn, String author, Integer price) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}

	public BookResponse(String message, List<BookResponse> resList) {
		super();
		this.message = message;
		this.resList = resList;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public List<BookResponse> getResList() {
		return resList;
	}

	public void setResList(List<BookResponse> resList) {
		this.resList = resList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookResponse(String message) {
		super();
		this.message = message;
	}

}
