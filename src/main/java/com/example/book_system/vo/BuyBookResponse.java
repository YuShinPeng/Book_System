package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyBookResponse {
	// �ѦW
	private String name;
	// ���y�s�X
	private String isbn;
	// �@��
	private String author;
	// ����
	private Integer price;
	// �ƶq
	private Integer amount;
	// �`���B
	private Integer totalPrice;
	// �w�s�q
	private Integer inventory;
	// �P��q
	private Integer sales;
	// �T��
	private String message;
	// �^�Ǥ�List
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
