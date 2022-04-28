package com.library.management.service;

import java.util.Optional;
import com.library.management.model.User;
import com.library.management.dto.UserDTO;
import com.library.management.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.dao.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Override
	public Optional<User> findById(Integer readerId) {
		return userRepository.findById(readerId);
	}

	@Override
	public void save(UserDTO user) {
		userRepository.save(UserMapper.INSTANCE.dtoToEntity(user));
	}

}
