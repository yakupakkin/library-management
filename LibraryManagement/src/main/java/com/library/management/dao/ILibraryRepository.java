package com.library.management.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.management.model.Book;

@Repository
public interface ILibraryRepository extends CrudRepository<Book, Integer> {

}
