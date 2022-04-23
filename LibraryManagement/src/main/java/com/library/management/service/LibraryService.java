package com.library.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.dao.ILibraryRepository;
import com.library.management.dto.BookDTO;
import com.library.management.model.Book;

@Service
public class LibraryService implements ILibraryService {

	private final Logger logger = LoggerFactory.getLogger(LibraryService.class);
	@Autowired
	ILibraryRepository libraryRepository;

	public void save(BookDTO book) {
		if (book.getId() == null) {
			Book newbook = new Book();
			newbook.setAuthor(book.getAuthor());
			newbook.setName(book.getName());
			libraryRepository.save(newbook);
			logger.info("Book saved!");
		} else {
			Optional<Book> bookOptional = libraryRepository.findById(book.getId());
			if (bookOptional.isPresent()) {
				Book bookUpdate = bookOptional.get();
				bookUpdate.setName(book.getName());
				bookUpdate.setAuthor(book.getAuthor());
				libraryRepository.save(bookUpdate);
			}
		}

	}

	public List<Book> findAll() {
		ArrayList<Book> result = (ArrayList<Book>) libraryRepository.findAll();

		if (!result.isEmpty()) {
			logger.info(result.toString());
			return result;
		} else {
			return new ArrayList<>();
		}
	}

}
