package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.dto.BookDTO;
import com.library.management.dto.ReaderDTO;
import com.library.management.service.ILibraryService;
import com.library.management.service.IReaderService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

	@Autowired
	ILibraryService libraryService;

	@Autowired
	IReaderService readerService;

	@PostMapping("/book")
	public void create(@RequestBody BookDTO book) {
		libraryService.save(book);
	}

	@GetMapping("/book")
	public List<BookDTO> read() {
		return (List<BookDTO>) libraryService.findAll();
	}

	@PostMapping("/reader")
	public void createReader(@RequestBody ReaderDTO reader) {
		readerService.save(reader);
	}

	@PutMapping("/reader/{readerId}/{bookId}")
	public BookDTO borrowBook(@PathVariable("readerId") Integer readerId, @PathVariable("bookId") Integer bookId) {
		return libraryService.borrowBook(readerId, bookId);
	}

}
