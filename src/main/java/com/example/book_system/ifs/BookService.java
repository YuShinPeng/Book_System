package com.example.book_system.ifs;

import java.util.List;

import com.example.book_system.entity.Book;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BuyBookRequest;
import com.example.book_system.vo.BuyBookResponse;
import com.example.book_system.vo.TypeResponse;

public interface BookService {
	// 新增書籍
	public BookResponse addBook(BookRequest bookRequest);

	// 修改書籍
	public BookResponse updateBook(BookRequest bookRequest);

	// 以分類搜尋書籍
	public List<TypeResponse> findByType(String Type);

	// 書籍搜尋-消費者
	public BookResponse customerFindByNameOrIsbnOrAuthor(String name, String isbn, String author);

	// 書籍搜尋-書籍商
	public BookResponse sellerFindByNameOrIsbnOrAuthor(String name, String isbn, String author);

	// 更新書籍資料-庫存量
	public BookResponse updateInventory(String isbn, int inventory);

	// 更新書籍資料-價格
	public BookResponse updatePrice(String isbn, int price);

	// 書籍銷售-消費者購買(可多本)
	public BuyBookResponse buyBook(BuyBookRequest buyBookRequest);

	// 書籍銷售-暢銷書排行榜(前五排序 1~5名)
	public BookResponse findByOrderBySalesDesc(int sales);


}
