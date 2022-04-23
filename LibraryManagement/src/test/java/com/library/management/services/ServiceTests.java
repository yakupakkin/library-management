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
import com.library.management.model.Book;
import com.library.management.service.LibraryService;

@ExtendWith(MockitoExtension.class)
class ServiceTests {

	@Mock
	ILibraryRepository libraryRepository;

	@InjectMocks
	LibraryService libraryService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateOrSaveBook() {
		Book book = new Book(1, "Java", "Yakup Akkin");

		libraryService.save(book);
		verify(libraryRepository, times(1)).save(book);
	}

	@Test
	void testFindAllBooks() {
		ArrayList<Book> list = new ArrayList<>();
		list.add(new Book("Python", "John Doe"));
		when(libraryRepository.findAll()).thenReturn(list);

		// test
		List<Book> bookList = libraryService.findAll();

		assertEquals(1, bookList.size());
		verify(libraryRepository, times(1)).findAll();
	}

}
