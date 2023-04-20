package com.example.book_system.ifs;

import java.util.List;

import com.example.book_system.entity.Book;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BuyBookRequest;
import com.example.book_system.vo.BuyBookResponse;
import com.example.book_system.vo.TypeResponse;

public interface BookService {
	// �s�W���y
	public BookResponse addBook(BookRequest bookRequest);

	// �ק���y
	public BookResponse updateBook(BookRequest bookRequest);

	// �H�����j�M���y
	public List<TypeResponse> findByType(String Type);

	// ���y�j�M-���O��
	public BookResponse customerFindByNameOrIsbnOrAuthor(String name, String isbn, String author);

	// ���y�j�M-���y��
	public BookResponse sellerFindByNameOrIsbnOrAuthor(String name, String isbn, String author);

	// ��s���y���-�w�s�q
	public BookResponse updateInventory(String isbn, int inventory);

	// ��s���y���-����
	public BookResponse updatePrice(String isbn, int price);

	// ���y�P��-���O���ʶR(�i�h��)
	public BuyBookResponse buyBook(BuyBookRequest buyBookRequest);

	// ���y�P��-�Z�P�ѱƦ�](�e���Ƨ� 1~5�W)
	public BookResponse findByOrderBySalesDesc(int sales);


}
