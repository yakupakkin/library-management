package com.library.management.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.library.management.dto.UserDTO;
import com.library.management.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.management.dao.ILibraryRepository;
import com.library.management.dao.IUserRepository;
import com.library.management.dto.BookDTO;
import com.library.management.mapper.BookMapper;
import com.library.management.model.Book;
import com.library.management.service.LibraryService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ServiceTests {

	@Mock
	ILibraryRepository libraryRepository;
	@Mock
	IUserRepository userRepository;
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
		UserDTO user = new UserDTO(1, "Yakup", "Akkin", 0);
		userRepository.save(UserMapper.INSTANCE.dtoToEntity(user));
		verify(userRepository, times(1)).save(UserMapper.INSTANCE.dtoToEntity(user));
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
	void testCreateBookReader() {
		//given
		BookDTO book = new BookDTO(1, "Java", "Yakup Akkin", false);
		UserDTO userDTO = new UserDTO(1, "Yakup", "Akkın", 0);
		//when
		libraryRepository.save(BookMapper.INSTANCE.dtoToEntity(book));
		userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDTO));
 		//then
		verify(libraryRepository, times(1)).save(BookMapper.INSTANCE.dtoToEntity(book));
		verify(userRepository, times(1)).save(UserMapper.INSTANCE.dtoToEntity(userDTO));
	}

}
