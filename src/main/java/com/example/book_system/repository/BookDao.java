package com.example.book_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_system.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, String> {
	
	//書籍分類搜尋
	List<Book> findByType(String type);
	
	//書籍搜尋-消費者&書籍商
	List<Book> findByNameOrIsbnOrAuthor(String name,String isbn,String author);
	
	// 書籍銷售-暢銷書排行榜(前五排序 1~5名)
	List<Book> findTop5ByOrderBySalesDesc();
	
	
	
	
}
