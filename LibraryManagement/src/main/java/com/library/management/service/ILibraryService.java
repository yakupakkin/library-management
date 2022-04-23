package com.library.management.service;

import com.library.management.dto.BookDTO;

public interface ILibraryService {

	void save(BookDTO book);

	Iterable<BookDTO> findAll();

	BookDTO borrowBook(Integer readerId, Integer bookId);

}
