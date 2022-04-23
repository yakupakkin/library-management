package com.library.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.dao.IReaderRepository;
import com.library.management.dto.ReaderDTO;
import com.library.management.mapper.ReaderMapper;
import com.library.management.model.Reader;

@Service
public class ReaderService implements IReaderService {

	@Autowired
	IReaderRepository readerRepository;

	@Override
	public Optional<Reader> findById(Integer readerId) {
		return readerRepository.findById(readerId);
	}

	@Override
	public void save(ReaderDTO reader) {
		readerRepository.save(ReaderMapper.INSTANCE.dtoToEntity(reader));
	}

}
