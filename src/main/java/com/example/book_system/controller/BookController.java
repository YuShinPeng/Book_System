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

	// 新增書籍
	@PostMapping("add_book")
	public BookResponse addBook(@RequestBody BookRequest bookRequest) {
		return bookService.addBook(bookRequest);
	}

	// 修改書籍
	@PostMapping("update_book")
	public BookResponse updateBook(@RequestBody BookRequest bookRequest) {
		return bookService.updateBook(bookRequest);
	}

	// 以分類搜尋書籍
	@PostMapping("search_book")
	public List<TypeResponse> findByType(@RequestBody BookRequest bookRequest) {
		return bookService.findByType(bookRequest.getType());
	}

	// 書籍搜尋-消費者
	@PostMapping("customer_find_by_name_or_isbn_or_author")
	public BookResponse customerFindByNameOrIsbnOrAuthor(@RequestBody BookRequest bookRequest) {
		return bookService.customerFindByNameOrIsbnOrAuthor(bookRequest.getName(), bookRequest.getIsbn(),
				bookRequest.getAuthor());
	}

	// 書籍搜尋-書籍商
	@PostMapping("seller_find_by_name_or_isbn_or_author")
	public BookResponse sellerFindByNameOrIsbnOrAuthor(@RequestBody BookRequest bookRequest) {
		return bookService.sellerFindByNameOrIsbnOrAuthor(bookRequest.getName(), bookRequest.getIsbn(),
				bookRequest.getAuthor());
	}

	// 更新書籍資料-庫存量
	@PostMapping("update_inventory")
	public BookResponse updateInventory(@RequestBody BookRequest bookRequest) {
		return bookService.updateInventory(bookRequest.getIsbn(), bookRequest.getInventory());
	}

	// 更新書籍資料-價格
	@PostMapping("update_price")
	public BookResponse updatePrice(@RequestBody BookRequest bookRequest) {
		return bookService.updatePrice(bookRequest.getIsbn(), bookRequest.getPrice());
	}

	// 書籍銷售-消費者購買(可多本)
	@PostMapping("buy_book")
	public BuyBookResponse buyBook(@RequestBody BuyBookRequest buyBookRequest) {
		return bookService.buyBook(buyBookRequest);
	}

	// 書籍銷售-暢銷書排行榜(前五排序 1~5名)
	@PostMapping("find_by_order_by_sales_desc")
	public BookResponse findByOrderBySalesDesc(@RequestBody BookRequest bookRequest) {
		return bookService.findByOrderBySalesDesc(bookRequest.getSales());
	}

}
