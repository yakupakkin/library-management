package com.library.management.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.library.management.dao.ILibraryRepository;
import com.library.management.dao.IReaderRepository;
import com.library.management.dto.BookDTO;
import com.library.management.dto.ReaderDTO;
import com.library.management.mapper.BookMapper;
import com.library.management.mapper.ReaderMapper;
import com.library.management.model.Book;
import com.library.management.service.LibraryService;

@ExtendWith(MockitoExtension.class)
class ServiceTests {

	@Mock
	ILibraryRepository libraryRepository;
	@Mock
	IReaderRepository readerRepository;
	@InjectMocks
	LibraryService libraryService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateOrSaveBook() {
		BookDTO book = new BookDTO(1, "Java", "Yakup Akkin", false);

		libraryRepository.save(BookMapper.INSTANCE.dtoToEntity(book));
		verify(libraryRepository, times(1)).save(BookMapper.INSTANCE.dtoToEntity(book));
	}

	@Test
	void testCreateOrSaveReader() {
		ReaderDTO reader = new ReaderDTO(1, "Yakup", "Akkin", 0);
		readerRepository.save(ReaderMapper.INSTANCE.dtoToEntity(reader));
		verify(readerRepository, times(1)).save(ReaderMapper.INSTANCE.dtoToEntity(reader));
	}

	@Test
	void testFindAllBooks() {
		ArrayList<Book> list = new ArrayList<>();
		list.add(new Book("Python", "John Doe", false));
		when(libraryRepository.findAll()).thenReturn(list);

		// test
		List<BookDTO> bookList = libraryService.findAll();

		assertEquals(1, bookList.size());
		verify(libraryRepository, times(1)).findAll();
	}

	@Test
	void borrowBook() {
		BookDTO book = new BookDTO(1, "Java", "Yakup Akkin", true);
		ReaderDTO readerDTO = new ReaderDTO(1, "Yakup", "Akkın", 0);
		libraryService.borrowBook(readerDTO.getId(), book.getId());

		readerRepository.findById(readerDTO.getId());
		verify(libraryRepository, times(1)).findById(book.getId());
		verify(readerRepository, times(1)).findById(readerDTO.getId());
	}

}
