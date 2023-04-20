package com.example.book_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_system.entity.Book;
import com.example.book_system.ifs.BookService;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BuyBookRequest;
import com.example.book_system.vo.BuyBookResponse;
import com.example.book_system.vo.TypeResponse;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	// �s�W���y
	@PostMapping("add_book")
	public BookResponse addBook(@RequestBody BookRequest bookRequest) {
		return bookService.addBook(bookRequest);
	}

	// �ק���y
	@PostMapping("update_book")
	public BookResponse updateBook(@RequestBody BookRequest bookRequest) {
		return bookService.updateBook(bookRequest);
	}

	// �H�����j�M���y
	@PostMapping("search_book")
	public List<TypeResponse> findByType(@RequestBody BookRequest bookRequest) {
		return bookService.findByType(bookRequest.getType());
	}

	// ���y�j�M-���O��
	@PostMapping("customer_find_by_name_or_isbn_or_author")
	public BookResponse customerFindByNameOrIsbnOrAuthor(@RequestBody BookRequest bookRequest) {
		return bookService.customerFindByNameOrIsbnOrAuthor(bookRequest.getName(), bookRequest.getIsbn(),
				bookRequest.getAuthor());
	}

	// ���y�j�M-���y��
	@PostMapping("seller_find_by_name_or_isbn_or_author")
	public BookResponse sellerFindByNameOrIsbnOrAuthor(@RequestBody BookRequest bookRequest) {
		return bookService.sellerFindByNameOrIsbnOrAuthor(bookRequest.getName(), bookRequest.getIsbn(),
				bookRequest.getAuthor());
	}

	// ��s���y���-�w�s�q
	@PostMapping("update_inventory")
	public BookResponse updateInventory(@RequestBody BookRequest bookRequest) {
		return bookService.updateInventory(bookRequest.getIsbn(), bookRequest.getInventory());
	}

	// ��s���y���-����
	@PostMapping("update_price")
	public BookResponse updatePrice(@RequestBody BookRequest bookRequest) {
		return bookService.updatePrice(bookRequest.getIsbn(), bookRequest.getPrice());
	}

	// ���y�P��-���O���ʶR(�i�h��)
	@PostMapping("buy_book")
	public BuyBookResponse buyBook(@RequestBody BuyBookRequest buyBookRequest) {
		return bookService.buyBook(buyBookRequest);
	}

	// ���y�P��-�Z�P�ѱƦ�](�e���Ƨ� 1~5�W)
	@PostMapping("find_by_order_by_sales_desc")
	public BookResponse findByOrderBySalesDesc(@RequestBody BookRequest bookRequest) {
		return bookService.findByOrderBySalesDesc(bookRequest.getSales());
	}

}
