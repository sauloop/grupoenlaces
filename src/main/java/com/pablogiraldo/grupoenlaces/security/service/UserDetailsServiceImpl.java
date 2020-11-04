package com.pablogiraldo.grupoenlaces.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pablogiraldo.grupoenlaces.entity.User;
import com.pablogiraldo.grupoenlaces.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByName(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return UsuarioPrincipal.build(user);
	}
}
