package com.example.book_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_system.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, String> {
	
	//���y�����j�M
	List<Book> findByType(String type);
	
	//���y�j�M-���O��&���y��
	List<Book> findByNameOrIsbnOrAuthor(String name,String isbn,String author);
	
	// ���y�P��-�Z�P�ѱƦ�](�e���Ƨ� 1~5�W)
	List<Book> findTop5ByOrderBySalesDesc();
	
	
	
	
}
