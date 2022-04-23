package com.library.management.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.management.model.Book;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DaoTests {

	@Autowired
	ILibraryRepository libraryRepository;

	@Test
	void testCreateReadDelete() {
		Book book = new Book("Java", "Yakup Akkin");

		libraryRepository.save(book);

		Iterable<Book> books = libraryRepository.findAll();
		Assertions.assertThat(books).extracting(Book::getName).asList();

		libraryRepository.deleteAll();
		Assertions.assertThat(libraryRepository.findAll()).isEmpty();
	}
}
