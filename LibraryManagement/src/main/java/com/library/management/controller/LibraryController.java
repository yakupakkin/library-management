package com.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.model.Book;
import com.library.management.service.ILibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

	@Autowired
	ILibraryService libraryService;

	@PostMapping("/book")
	public void create(@RequestBody Book book) {
		libraryService.save(book);
	}

	@GetMapping("/book")
	public Iterable<Book> read() {
		return libraryService.findAll();
	}

}
