package com.library.management.mapper;

import com.library.management.dto.BookDTO;
import com.library.management.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDTO entityToDTO(Book book);
    Book dtoToEntity(BookDTO bookDTO);
    List<BookDTO> mapToDTOList(List<Book> books);
    List<Book> mapToList(List<BookDTO> books);
}
