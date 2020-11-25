package com.pablogiraldo.grupoenlaces.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pablogiraldo.grupoenlaces.entity.Link;

public interface LinkService {

	List<Link> list();

	Page<Link> list(Pageable linkPageable);

	void save(Link link);

	void delete(long id);

	Optional<Link> getById(long id);

	Optional<Link> getByTitle(String title);

	boolean existsById(long id);

	boolean existsByTitle(String title);
}
