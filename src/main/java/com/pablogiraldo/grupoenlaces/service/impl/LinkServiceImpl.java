package com.pablogiraldo.grupoenlaces.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablogiraldo.grupoenlaces.entity.Link;
import com.pablogiraldo.grupoenlaces.repository.LinkRepository;
import com.pablogiraldo.grupoenlaces.service.LinkService;

@Service
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkRepository linkRepository;

	@Override
	public List<Link> list() {
		return linkRepository.findAllByOrderByIdDesc();
	}

	@Override
	public void save(Link link) {
		linkRepository.save(link);
	}

	@Override
	public void delete(long id) {
		linkRepository.deleteById(id);
	}

	@Override
	public Optional<Link> getById(long id) {
		return linkRepository.findById(id);
	}

	@Override
	public Optional<Link> getByTitle(String title) {
		return linkRepository.findByTitle(title);
	}

	@Override
	public boolean existsById(long id) {
		return linkRepository.existsById(id);
	}

	@Override
	public boolean existsByTitle(String title) {
		return linkRepository.existsByTitle(title);
	}
}
