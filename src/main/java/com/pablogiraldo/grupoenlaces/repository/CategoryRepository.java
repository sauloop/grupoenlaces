package com.pablogiraldo.grupoenlaces.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablogiraldo.grupoenlaces.entity.Category;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	boolean existsById(int id);

	boolean existsByName(String name);

	List<Category> findAllByOrderByNameAsc();

	Optional<Category> findByName(String name);
}
