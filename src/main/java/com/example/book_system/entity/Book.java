package com.example.book_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	// ���y�s�X
	@Id
	@Column(name = "isbn")
	private String isbn;
	// �ѦW
	@Column(name = "name")
	private String name;
	// �@��
	@Column(name = "author")
	private String author;
	// ����
	@Column(name = "price")
	private int price;
	// �w�s�q
	@Column(name = "inventory")
	private int inventory;
	// �P��q
	@Column(name = "sales")
	private int sales;
	// ����
	@Column(name = "type")
	private String type;

	public Book() {

	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
