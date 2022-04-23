package com.library.management.service;

import com.library.management.dto.BookDTO;
import com.library.management.model.Book;

public interface ILibraryService {

	void save(BookDTO book);

	Iterable<Book> findAll();

}
