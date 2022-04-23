package com.library.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.dao.ILibraryRepository;
import com.library.management.dto.BookDTO;
import com.library.management.exception.BookNotFoundException;
import com.library.management.exception.ReaderNotFoundException;
import com.library.management.mapper.BookMapper;
import com.library.management.mapper.ReaderMapper;
import com.library.management.model.Book;
import com.library.management.model.Reader;

@Service
public class LibraryService implements ILibraryService {

	private final Logger logger = LoggerFactory.getLogger(LibraryService.class);
	@Autowired
	ILibraryRepository libraryRepository;
	@Autowired
	IReaderService readerService;

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

	public List<BookDTO> findAll() {
		Iterable<Book> result = libraryRepository.findAll();
		List<Book> resultList = StreamSupport.stream(result.spliterator(), false).filter(book -> !book.isReserved())
				.collect(Collectors.toList());

		if (!resultList.isEmpty()) {
			return BookMapper.INSTANCE.mapToDTOList(resultList);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public BookDTO borrowBook(Integer readerId, Integer bookId) {
		Optional<Book> optionalBook = libraryRepository.findById(bookId);
		if (optionalBook.isPresent() && !optionalBook.get().isReserved()) {
			Optional<Reader> optionalReader = readerService.findById(readerId);
			if (optionalReader.isPresent() && optionalReader.get().getReservedCount() < 2) {
				optionalBook.get().setReserved(true);
				libraryRepository.save(optionalBook.get());
				optionalReader.get().setReservedCount(optionalReader.get().getReservedCount() + 1);
				readerService.save(ReaderMapper.INSTANCE.entityToDTO(optionalReader.get()));
				return BookMapper.INSTANCE.entityToDTO(optionalBook.get());
			} else {
				throw new ReaderNotFoundException("Reader not found or has 2 books already! ");
			}

		} else {
			throw new BookNotFoundException("Book not found in library or reserved already!");
		}
	}

}
