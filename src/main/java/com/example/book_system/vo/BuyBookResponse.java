package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyBookResponse {
	// 書名
	private String name;
	// 書籍編碼
	private String isbn;
	// 作者
	private String author;
	// 價格
	private Integer price;
	// 數量
	private Integer amount;
	// 總金額
	private Integer totalPrice;
	// 庫存量
	private Integer inventory;
	// 銷售量
	private Integer sales;
	// 訊息
	private String message;
	// 回傳之List
	private List<Book> orderBook;

	public BuyBookResponse() {

	}

	public BuyBookResponse(List<Book> orderBook, Integer amount, Integer totalPrice) {
		super();
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.orderBook = orderBook;
	}

	public List<Book> getOrderBook() {
		return orderBook;
	}

	public void setOrderBook(List<Book> orderBook) {
		this.orderBook = orderBook;
	}

	public BuyBookResponse(Integer totalPrice) {
		super();
		this.totalPrice = totalPrice;
	}

	public BuyBookResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

}
