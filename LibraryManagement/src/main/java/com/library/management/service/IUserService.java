package com.library.management.service;

import java.util.Optional;

import com.library.management.dto.UserDTO;
import com.library.management.model.User;

public interface IUserService {

	Optional<User> findById(Integer readerId);

	void save(UserDTO userDTO);

}
