package com.library.management.dao;

import com.library.management.dto.UserDTO;
import com.library.management.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

	void save(UserDTO userDTO);

}
