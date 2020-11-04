package com.pablogiraldo.grupoenlaces.service;

import java.util.List;
import java.util.Optional;

import com.pablogiraldo.grupoenlaces.entity.Category;

public interface CategoryService {

	List<Category> list();

	void save(Category category);

	void delete(int id);

	Optional<Category> getById(int id);

	Optional<Category> getByName(String name);

	boolean existsById(int id);

	boolean existsByName(String name);
}
