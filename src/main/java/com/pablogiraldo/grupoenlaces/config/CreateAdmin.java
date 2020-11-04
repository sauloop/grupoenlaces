package com.pablogiraldo.grupoenlaces.config;

//import java.util.ArrayList;
//import java.util.List;
//import com.pablogiraldo.grupoenlaces.entity.Role;
//import com.pablogiraldo.grupoenlaces.entity.User;
//import com.pablogiraldo.grupoenlaces.enu.RoleName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pablogiraldo.grupoenlaces.service.RoleService;
import com.pablogiraldo.grupoenlaces.service.UserService;

@Service
public class CreateAdmin implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		String passwordEncoded = passwordEncoder.encode("admin");
//		user.setUsername("admin");
//		user.setPassword(passwordEncoded);
//		Role roleAdmin = roleService.getByName(RoleName.ROLE_ADMIN).get();
//		Role roleUser = roleService.getByName(RoleName.ROLE_USER).get();
//		List<Role> roles = new ArrayList<>();
//		roles.add(roleAdmin);
//		roles.add(roleUser);
//		user.setRoles(roles);
//		userService.save(user);
	}
}
