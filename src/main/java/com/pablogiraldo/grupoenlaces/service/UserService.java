package com.pablogiraldo.grupoenlaces.service;

import java.util.List;
import java.util.Optional;

import com.pablogiraldo.grupoenlaces.entity.User;

public interface UserService {

	List<User> list();

	void save(User user);

	void delete(long id);

	Optional<User> getById(long id);

	Optional<User> getByUsername(String name);

	boolean existsById(long id);

	boolean existsByUsername(String name);
}
