package com.library.management.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.library.management.dto.UserDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.library.management.controller.LibraryController;
import com.library.management.dto.BookDTO;
import com.library.management.service.LibraryService;
import com.library.management.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LibraryController.class)
class StandaloneControllerTests {

	@MockBean
	LibraryService libraryService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testFindAll() throws Exception {
		//given
		BookDTO book = new BookDTO(1, "Java", "Yakup Akkin", false);
		List<BookDTO> books = Arrays.asList(book);
		//when
		Mockito.when(libraryService.findAll()).thenReturn(books);
		//then
		mockMvc.perform(get("/api/library/book")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].name", Matchers.is("Java")));
	}

	@Test
	void testBorrowBook() throws Exception {
		//given
		BookDTO bookDTO = new BookDTO(1, "Java", "Yakup Akkin", false);
		UserDTO readerDTO = new UserDTO(1, "Yakup", "Akkın", 0);
		//when
		Mockito.when(libraryService.borrowBook(readerDTO.getId(), bookDTO.getId())).thenReturn(bookDTO);

		//then
		mockMvc.perform(put("/api/library/user/{userId}/{bookId}", 1, 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.aMapWithSize(4)));
	}
}
