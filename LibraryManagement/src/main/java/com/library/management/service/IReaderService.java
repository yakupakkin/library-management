package com.library.management.service;

import java.util.Optional;

import com.library.management.dto.ReaderDTO;
import com.library.management.model.Reader;

public interface IReaderService {

	Optional<Reader> findById(Integer readerId);

	void save(ReaderDTO reader);

}
