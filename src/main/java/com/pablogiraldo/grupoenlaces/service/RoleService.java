package com.pablogiraldo.grupoenlaces.service;

import java.util.List;
import java.util.Optional;

import com.pablogiraldo.grupoenlaces.entity.Role;
import com.pablogiraldo.grupoenlaces.enu.RoleName;

public interface RoleService {

	List<Role> list();

	void save(Role role);

	public Optional<Role> getByName(RoleName name);

	boolean existsByName(RoleName name);
}
