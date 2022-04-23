package com.library.management.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.management.model.Book;

@Repository
public class LibraryDao {

	@Autowired
	ILibraryRepository libraryRepository;

	public List<Book> getBookList() {
		Iterable<Book> iterableBook = libraryRepository.findAll();
		List<Book> result = new ArrayList<>();
		iterableBook.forEach(result::add);

		return result;
	}

	public void addBook(Book book) {
		libraryRepository.save(book);
	}
}
