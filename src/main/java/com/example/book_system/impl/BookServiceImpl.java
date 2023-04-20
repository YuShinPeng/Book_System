package com.example.book_system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.book_system.entity.Book;
import com.example.book_system.ifs.BookService;
import com.example.book_system.repository.BookDao;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BuyBookRequest;
import com.example.book_system.vo.BuyBookResponse;
import com.example.book_system.vo.TypeResponse;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	// 新增書籍
	@Override
	public BookResponse addBook(BookRequest bookRequest) {
		// 存放檢查的資料
		List<Book> bookList = bookRequest.getBookList();
		// 暫存已存在的書籍資料
		List<Book> errorList = new ArrayList<>();
		// 逐筆檢查
		for (Book item : bookList) {
			// 檢查isbn確認書是否存在
			if (!StringUtils.hasText(item.getIsbn())) {
				return new BookResponse("ISBN不得為空");
			}
			// 如果此書已存在存入暫放的List
			if (bookDao.existsById(item.getIsbn())) {
				errorList.add(item);
			}
		}
		// 如果存放錯誤的List不為空表示此書已存在回傳訊息
		if (!errorList.isEmpty()) {
			return new BookResponse(errorList, "此書已存在");
		}
		// 反之則新增書籍至資料庫並回傳新增成功訊息
		bookDao.saveAll(bookList);
		return new BookResponse(bookList, "新增成功");
	}

	// 修改書籍
	@Override
	public BookResponse updateBook(BookRequest bookRequest) {
		// 存放檢查的資料
		List<Book> bookList = bookRequest.getBookList();
		// 逐筆檢查
		for (Book item : bookList) {
			// 如果此書不存在則回傳訊息
			if (!StringUtils.hasText(item.getIsbn())) {
				return new BookResponse("此書不存在");
			}
			// 如果輸入的資料不為空則重新設定後set回去
			if (StringUtils.hasText(item.getAuthor())) {
				item.setAuthor(item.getAuthor());
			}
			if (StringUtils.hasText(item.getName())) {
				item.setName(item.getName());
			}
			if (StringUtils.hasText(item.getType())) {
				item.setType(item.getType());
			}
		}
		// 最後一次加回資料庫並回傳修改成功訊息
		bookDao.saveAll(bookList);
		return new BookResponse("修改成功");
	}

	// 以分類搜尋書籍
	@Override
	public List<TypeResponse> findByType(String Type) {
		
		List<Book> typeList = bookDao.findByType(Type);
		 // 裝所有回傳的書籍
		List<TypeResponse> resAllBook = new ArrayList<>();
		for (Book item : typeList) {
			// 裝搜尋的書
			TypeResponse resBook = new TypeResponse();
			// 找到的資料set回去後存放回傳書籍的List
			resBook.setName(item.getName());
			resBook.setIsbn(item.getIsbn());
			resBook.setAuthor(item.getAuthor());
			resBook.setPrice(item.getPrice());
			resBook.setInventory(item.getInventory());
			resAllBook.add(resBook);
		}
		// 回傳此書籍的資料
		return resAllBook;
	}

	// 書籍搜尋-消費者
	@Override
	public BookResponse customerFindByNameOrIsbnOrAuthor(String name, String isbn, String author) {
		// 可以透過書名或isbn或作者資料搜尋書籍，回傳資料(書名、isbn、作者、價格)
		List<Book> findBook = bookDao.findByNameOrIsbnOrAuthor(name, isbn, author);
		// 裝所有回傳的書籍
		List<BookResponse> resBook = new ArrayList<>(); 
		for (Book item : findBook) {
			// 裝搜尋的書
			BookResponse bookRes = new BookResponse(); 
			// 找到的資料set回去後存放回傳書籍的List
			bookRes.setName(item.getName());
			bookRes.setIsbn(item.getIsbn());
			bookRes.setAuthor(item.getAuthor());
			bookRes.setPrice(item.getPrice());
			resBook.add(bookRes);
		}
		// 回傳此書籍的資料和訊息
		return new BookResponse("已找到", resBook);
	}

	// 書籍搜尋-書籍商
	@Override
	public BookResponse sellerFindByNameOrIsbnOrAuthor(String name, String isbn, String author) {
		List<Book> findBook = bookDao.findByNameOrIsbnOrAuthor(name, isbn, author);
		 // 裝所有回傳的書籍
		List<BookResponse> resBook = new ArrayList<>();
		for (Book item : findBook) {
			 // 裝搜尋的書
			BookResponse bookRes = new BookResponse();
			// 找到的資料set回去後存放回傳書籍的List
			bookRes.setName(item.getName());
			bookRes.setIsbn(item.getIsbn());
			bookRes.setAuthor(item.getAuthor());
			bookRes.setPrice(item.getPrice());
			bookRes.setInventory(item.getInventory());
			resBook.add(bookRes);
		}
		// 回傳此書籍的資料和訊息
		return new BookResponse("已找到", resBook);
	}

	// 更新書籍資料-庫存量
	@Override
	public BookResponse updateInventory(String isbn, int inventory) {
		// 找書
		Optional<Book> findBook = bookDao.findById(isbn);
		// 檢查此isbn是否存在
		if (!findBook.isPresent()) {
			return new BookResponse("此書不存在");
		}
		// 存在取得此書資料
		Book getBook = findBook.get();
		int initial = getBook.getInventory();
		// 原有的庫存量加上新的庫存量
		getBook.setInventory(initial + inventory);
		// 儲存回去資料庫
		bookDao.save(getBook);
		// 回傳更新後的書籍資訊
		return new BookResponse(getBook.getName(), getBook.getIsbn(), getBook.getAuthor(), getBook.getPrice(),
				getBook.getInventory());
	}

	// 更新書籍資料-價格
	@Override
	public BookResponse updatePrice(String isbn, int price) {
		// 找書
		Optional<Book> findBook = bookDao.findById(isbn);
		// 檢查此isbn是否存在
		if (!findBook.isPresent()) {
			return new BookResponse("此書不存在");
		}
		// 存在取得此書資料
		Book getBook = findBook.get();
		int initial = getBook.getPrice();
		// 原有的價格修改為新的價格
		getBook.setPrice(price);
		// 儲存回去資料庫
		bookDao.save(getBook);
		// 回傳更新後的書籍資訊
		return new BookResponse(getBook.getName(), getBook.getIsbn(), getBook.getAuthor(), getBook.getPrice());
	}

	// 書籍銷售-消費者購買(可多本)
	@Override
	public BuyBookResponse buyBook(BuyBookRequest buyBookRequest) {
		// key為isbn value是購買數量
		Map<String, Integer> buyBook = buyBookRequest.getBuyMap();
		// 回傳書籍訊息資料之List
		List<Book> orderBook = new ArrayList<>();
		// 總價格初始為0
		int totalPrice = 0;
		int amount = 0;
		for (Entry<String, Integer> item : buyBook.entrySet()) {
			// 取得Key(isbn)
			String buyItem = item.getKey();
			// 取得value(數量)
			int amountItem = item.getValue();
			// 檢查此書是否存在
			Optional<Book> bookOpt = bookDao.findById(buyItem);
			if (!bookOpt.isPresent()) {
				return new BuyBookResponse("此書不存在");
			}
			// 取得此書訊息
			Book getBook = bookOpt.get();
			// 若庫存量為0則無法購買
			if (getBook.getInventory() == 0) {
				return new BuyBookResponse("此書已售完");
			}
			// 若購買數量大於庫存則無法購買
			if (amountItem > getBook.getInventory()) {
				return new BuyBookResponse("購買數量大於庫存量");
			}
			// 庫存-購買數量
			int newInventory = getBook.getInventory() - amountItem;
			// 銷售+購買數量
			int newSales = getBook.getSales() + amountItem;
			// 取得此書價格
			int price = getBook.getPrice();
			// 計算此次金額
			int itemTotalPrice = price * amountItem;
			// 加入至總金額
			totalPrice += itemTotalPrice;
			// 設定數量
			amount += amountItem;
			// 設定回去
			getBook.setInventory(newInventory);
			getBook.setSales(newSales);
			getBook.setName(getBook.getName());
			getBook.setIsbn(getBook.getIsbn());
			getBook.setAuthor(getBook.getAuthor());
			getBook.setPrice(getBook.getPrice());
			bookDao.save(getBook);
			orderBook.add(getBook);
		}
		return new BuyBookResponse(orderBook,amount,totalPrice);
	}

	// 書籍銷售-暢銷書排行榜(前五排序 1~5名)
	@Override
	public BookResponse findByOrderBySalesDesc(int sales) {
		List<Book> bookReq = bookDao.findTop5ByOrderBySalesDesc();
		// 裝回傳之書籍
		List<BookResponse> bookFind = new ArrayList<>(); 
		for (Book item : bookReq) {
			 // 裝搜尋的書
			BookResponse bookRes = new BookResponse();
			// 找到的資料set回去後存放回傳書籍的List
			bookRes.setName(item.getName());
			bookRes.setIsbn(item.getIsbn());
			bookRes.setAuthor(item.getAuthor());
			bookRes.setPrice(item.getPrice());
			bookFind.add(bookRes);
		}
		// 回傳訊息及書籍排名資料
		return new BookResponse("已找到", bookFind);
	}

}
