package com.library.management.service;

import com.library.management.model.Book;

public interface ILibraryService {

	void save(Book book);

	Iterable<Book> findAll();

}
