package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeResponse {
	// 書名
	private String name;
	// 書籍編碼
	private String isbn;
	// 作者
	private String author;
	// 價格
	private int price;
	// 訊息
	private String message;
	// 庫存量
	private int Inventory;

	private List<TypeResponse> typeList;

	public TypeResponse() {

	}
	
	public TypeResponse(String name, String isbn, String author, int price) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}

	public TypeResponse(List<TypeResponse> typeList, String message) {
		super();
		this.message = message;
		this.typeList = typeList;
	}


	public int getInventory() {
		return Inventory;
	}

	public void setInventory(int inventory) {
		Inventory = inventory;
	}

	public List<TypeResponse> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<TypeResponse> typeList) {
		this.typeList = typeList;
	}

	public TypeResponse(String message) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


}
