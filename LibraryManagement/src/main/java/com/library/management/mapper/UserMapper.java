package com.library.management.mapper;

import java.util.List;

import com.library.management.dto.UserDTO;
import com.library.management.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO entityToDTO(User book);

    User dtoToEntity(UserDTO bookDTO);

    List<UserDTO> mapToDTOList(List<User> books);

    List<User> mapToList(List<UserDTO> books);
}
