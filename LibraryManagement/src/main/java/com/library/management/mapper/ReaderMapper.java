package com.library.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.library.management.dto.ReaderDTO;
import com.library.management.model.Reader;

@Mapper
public interface ReaderMapper {
	ReaderMapper INSTANCE = Mappers.getMapper(ReaderMapper.class);

	ReaderDTO entityToDTO(Reader book);

	Reader dtoToEntity(ReaderDTO bookDTO);

	List<ReaderDTO> mapToDTOList(List<Reader> books);

	List<Reader> mapToList(List<ReaderDTO> books);
}
