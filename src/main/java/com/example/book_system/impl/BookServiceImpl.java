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

	// �s�W���y
	@Override
	public BookResponse addBook(BookRequest bookRequest) {
		// �s���ˬd�����
		List<Book> bookList = bookRequest.getBookList();
		// �Ȧs�w�s�b�����y���
		List<Book> errorList = new ArrayList<>();
		// �v���ˬd
		for (Book item : bookList) {
			// �ˬdisbn�T�{�ѬO�_�s�b
			if (!StringUtils.hasText(item.getIsbn())) {
				return new BookResponse("ISBN���o����");
			}
			// �p�G���Ѥw�s�b�s�J�ȩ�List
			if (bookDao.existsById(item.getIsbn())) {
				errorList.add(item);
			}
		}
		// �p�G�s����~��List�����Ū�ܦ��Ѥw�s�b�^�ǰT��
		if (!errorList.isEmpty()) {
			return new BookResponse(errorList, "���Ѥw�s�b");
		}
		// �Ϥ��h�s�W���y�ܸ�Ʈw�æ^�Ƿs�W���\�T��
		bookDao.saveAll(bookList);
		return new BookResponse(bookList, "�s�W���\");
	}

	// �ק���y
	@Override
	public BookResponse updateBook(BookRequest bookRequest) {
		// �s���ˬd�����
		List<Book> bookList = bookRequest.getBookList();
		// �v���ˬd
		for (Book item : bookList) {
			// �p�G���Ѥ��s�b�h�^�ǰT��
			if (!StringUtils.hasText(item.getIsbn())) {
				return new BookResponse("���Ѥ��s�b");
			}
			// �p�G��J����Ƥ����ūh���s�]�w��set�^�h
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
		// �̫�@���[�^��Ʈw�æ^�ǭק令�\�T��
		bookDao.saveAll(bookList);
		return new BookResponse("�ק令�\");
	}

	// �H�����j�M���y
	@Override
	public List<TypeResponse> findByType(String Type) {
		
		List<Book> typeList = bookDao.findByType(Type);
		 // �˩Ҧ��^�Ǫ����y
		List<TypeResponse> resAllBook = new ArrayList<>();
		for (Book item : typeList) {
			// �˷j�M����
			TypeResponse resBook = new TypeResponse();
			// ��쪺���set�^�h��s��^�Ǯ��y��List
			resBook.setName(item.getName());
			resBook.setIsbn(item.getIsbn());
			resBook.setAuthor(item.getAuthor());
			resBook.setPrice(item.getPrice());
			resBook.setInventory(item.getInventory());
			resAllBook.add(resBook);
		}
		// �^�Ǧ����y�����
		return resAllBook;
	}

	// ���y�j�M-���O��
	@Override
	public BookResponse customerFindByNameOrIsbnOrAuthor(String name, String isbn, String author) {
		// �i�H�z�L�ѦW��isbn�Χ@�̸�Ʒj�M���y�A�^�Ǹ��(�ѦW�Bisbn�B�@�̡B����)
		List<Book> findBook = bookDao.findByNameOrIsbnOrAuthor(name, isbn, author);
		// �˩Ҧ��^�Ǫ����y
		List<BookResponse> resBook = new ArrayList<>(); 
		for (Book item : findBook) {
			// �˷j�M����
			BookResponse bookRes = new BookResponse(); 
			// ��쪺���set�^�h��s��^�Ǯ��y��List
			bookRes.setName(item.getName());
			bookRes.setIsbn(item.getIsbn());
			bookRes.setAuthor(item.getAuthor());
			bookRes.setPrice(item.getPrice());
			resBook.add(bookRes);
		}
		// �^�Ǧ����y����ƩM�T��
		return new BookResponse("�w���", resBook);
	}

	// ���y�j�M-���y��
	@Override
	public BookResponse sellerFindByNameOrIsbnOrAuthor(String name, String isbn, String author) {
		List<Book> findBook = bookDao.findByNameOrIsbnOrAuthor(name, isbn, author);
		 // �˩Ҧ��^�Ǫ����y
		List<BookResponse> resBook = new ArrayList<>();
		for (Book item : findBook) {
			 // �˷j�M����
			BookResponse bookRes = new BookResponse();
			// ��쪺���set�^�h��s��^�Ǯ��y��List
			bookRes.setName(item.getName());
			bookRes.setIsbn(item.getIsbn());
			bookRes.setAuthor(item.getAuthor());
			bookRes.setPrice(item.getPrice());
			bookRes.setInventory(item.getInventory());
			resBook.add(bookRes);
		}
		// �^�Ǧ����y����ƩM�T��
		return new BookResponse("�w���", resBook);
	}

	// ��s���y���-�w�s�q
	@Override
	public BookResponse updateInventory(String isbn, int inventory) {
		// ���
		Optional<Book> findBook = bookDao.findById(isbn);
		// �ˬd��isbn�O�_�s�b
		if (!findBook.isPresent()) {
			return new BookResponse("���Ѥ��s�b");
		}
		// �s�b���o���Ѹ��
		Book getBook = findBook.get();
		int initial = getBook.getInventory();
		// �즳���w�s�q�[�W�s���w�s�q
		getBook.setInventory(initial + inventory);
		// �x�s�^�h��Ʈw
		bookDao.save(getBook);
		// �^�ǧ�s�᪺���y��T
		return new BookResponse(getBook.getName(), getBook.getIsbn(), getBook.getAuthor(), getBook.getPrice(),
				getBook.getInventory());
	}

	// ��s���y���-����
	@Override
	public BookResponse updatePrice(String isbn, int price) {
		// ���
		Optional<Book> findBook = bookDao.findById(isbn);
		// �ˬd��isbn�O�_�s�b
		if (!findBook.isPresent()) {
			return new BookResponse("���Ѥ��s�b");
		}
		// �s�b���o���Ѹ��
		Book getBook = findBook.get();
		int initial = getBook.getPrice();
		// �즳������קאּ�s������
		getBook.setPrice(price);
		// �x�s�^�h��Ʈw
		bookDao.save(getBook);
		// �^�ǧ�s�᪺���y��T
		return new BookResponse(getBook.getName(), getBook.getIsbn(), getBook.getAuthor(), getBook.getPrice());
	}

	// ���y�P��-���O���ʶR(�i�h��)
	@Override
	public BuyBookResponse buyBook(BuyBookRequest buyBookRequest) {
		// key��isbn value�O�ʶR�ƶq
		Map<String, Integer> buyBook = buyBookRequest.getBuyMap();
		// �^�Ǯ��y�T����Ƥ�List
		List<Book> orderBook = new ArrayList<>();
		// �`�����l��0
		int totalPrice = 0;
		int amount = 0;
		for (Entry<String, Integer> item : buyBook.entrySet()) {
			// ���oKey(isbn)
			String buyItem = item.getKey();
			// ���ovalue(�ƶq)
			int amountItem = item.getValue();
			// �ˬd���ѬO�_�s�b
			Optional<Book> bookOpt = bookDao.findById(buyItem);
			if (!bookOpt.isPresent()) {
				return new BuyBookResponse("���Ѥ��s�b");
			}
			// ���o���ѰT��
			Book getBook = bookOpt.get();
			// �Y�w�s�q��0�h�L�k�ʶR
			if (getBook.getInventory() == 0) {
				return new BuyBookResponse("���Ѥw�⧹");
			}
			// �Y�ʶR�ƶq�j��w�s�h�L�k�ʶR
			if (amountItem > getBook.getInventory()) {
				return new BuyBookResponse("�ʶR�ƶq�j��w�s�q");
			}
			// �w�s-�ʶR�ƶq
			int newInventory = getBook.getInventory() - amountItem;
			// �P��+�ʶR�ƶq
			int newSales = getBook.getSales() + amountItem;
			// ���o���ѻ���
			int price = getBook.getPrice();
			// �p�⦹�����B
			int itemTotalPrice = price * amountItem;
			// �[�J���`���B
			totalPrice += itemTotalPrice;
			// �]�w�ƶq
			amount += amountItem;
			// �]�w�^�h
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

	// ���y�P��-�Z�P�ѱƦ�](�e���Ƨ� 1~5�W)
	@Override
	public BookResponse findByOrderBySalesDesc(int sales) {
		List<Book> bookReq = bookDao.findTop5ByOrderBySalesDesc();
		// �˦^�Ǥ����y
		List<BookResponse> bookFind = new ArrayList<>(); 
		for (Book item : bookReq) {
			 // �˷j�M����
			BookResponse bookRes = new BookResponse();
			// ��쪺���set�^�h��s��^�Ǯ��y��List
			bookRes.setName(item.getName());
			bookRes.setIsbn(item.getIsbn());
			bookRes.setAuthor(item.getAuthor());
			bookRes.setPrice(item.getPrice());
			bookFind.add(bookRes);
		}
		// �^�ǰT���ή��y�ƦW���
		return new BookResponse("�w���", bookFind);
	}

}
