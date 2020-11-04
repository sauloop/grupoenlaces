package com.pablogiraldo.grupoenlaces.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablogiraldo.grupoenlaces.entity.Category;
import com.pablogiraldo.grupoenlaces.repository.CategoryRepository;
import com.pablogiraldo.grupoenlaces.service.CategoryService;

@Service
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> list() {
		return categoryRepository.findAllByOrderByNameAsc();
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Optional<Category> getById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Optional<Category> getByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public boolean existsById(int id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public boolean existsByName(String name) {
		return categoryRepository.existsByName(name);
	}
}
