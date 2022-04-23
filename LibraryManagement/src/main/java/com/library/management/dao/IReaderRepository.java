package com.library.management.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.management.dto.ReaderDTO;
import com.library.management.model.Reader;

@Repository
public interface IReaderRepository extends CrudRepository<Reader, Integer> {

	void save(ReaderDTO reader);

}
