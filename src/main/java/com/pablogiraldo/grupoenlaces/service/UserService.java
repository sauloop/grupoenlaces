package com.pablogiraldo.grupoenlaces.service;

import java.util.List;
import java.util.Optional;

import com.pablogiraldo.grupoenlaces.entity.User;

public interface UserService {

	List<User> list();

	Optional<User> getById(long id);

	Optional<User> getByName(String name);

	void save(User user);

	void delete(long id);

	boolean existsById(long id);

	boolean existsByName(String name);
}
