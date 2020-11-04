package com.pablogiraldo.grupoenlaces.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablogiraldo.grupoenlaces.entity.Role;
import com.pablogiraldo.grupoenlaces.enu.RoleName;
import com.pablogiraldo.grupoenlaces.repository.RoleRepository;
import com.pablogiraldo.grupoenlaces.service.RoleService;

@Service
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> list() {
		return roleRepository.findAll();
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Optional<Role> getByName(RoleName name) {
		return roleRepository.findByName(name);
	}

	@Override
	public boolean existsByName(RoleName name) {
		return roleRepository.existsByName(name);
	}
}
