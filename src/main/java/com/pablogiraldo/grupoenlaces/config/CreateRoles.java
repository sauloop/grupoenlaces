package com.pablogiraldo.grupoenlaces.config;

//import com.pablogiraldo.grupoenlaces.entity.Role;
//import com.pablogiraldo.grupoenlaces.enu.RoleName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.pablogiraldo.grupoenlaces.service.RoleService;

@Service
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
//		Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
//		Role roleUser = new Role(RoleName.ROLE_USER);
//		Role roleEditor = new Role(RoleName.ROLE_EDITOR);
//		roleService.save(roleAdmin);
//		roleService.save(roleUser);
//		roleService.save(roleEditor);
	}
}
