package com.library.management.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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
import com.library.management.model.Book;
import com.library.management.service.LibraryService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LibraryController.class)
class StandaloneControllerTests {

	@MockBean
	LibraryService libraryService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testfindAll() throws Exception {
		Book book = new Book("Java", "Yakup Akkin");
		List<Book> books = Arrays.asList(book);

		Mockito.when(libraryService.findAll()).thenReturn(books);

		mockMvc.perform(get("/api/library/book")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].name", Matchers.is("Java")));
	}
}
