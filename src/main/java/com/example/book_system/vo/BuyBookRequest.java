package com.example.book_system.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyBookRequest {
	// �ѦW
	private String name;
	// ���y�s�X
	private String isbn;
	// �@��
	private String author;
	// ����
	private Integer price;
	// �ʶR�ƶq
	private Integer amount;
	// �`���B
	private Integer totalPrice;
	// key��isbn value���ʶR�ƶq
	private Map<String, Integer> buyMap;

	public BuyBookRequest() {

	}

	public Map<String, Integer> getBuyMap() {
		return buyMap;
	}

	public void setBuyMap(Map<String, Integer> buyMap) {
		this.buyMap = buyMap;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
