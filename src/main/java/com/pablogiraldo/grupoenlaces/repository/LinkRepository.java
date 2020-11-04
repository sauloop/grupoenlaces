package com.pablogiraldo.grupoenlaces.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablogiraldo.grupoenlaces.entity.Link;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface LinkRepository extends JpaRepository<Link, Long> {

	List<Link> findAllByOrderByIdDesc();

	boolean existsById(long id);

	boolean existsByTitle(String title);

	Optional<Link> findByTitle(String title);
}
