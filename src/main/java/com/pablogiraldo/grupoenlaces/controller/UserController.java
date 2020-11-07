package com.pablogiraldo.grupoenlaces.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pablogiraldo.grupoenlaces.entity.Role;
import com.pablogiraldo.grupoenlaces.entity.User;
import com.pablogiraldo.grupoenlaces.enu.RoleName;
import com.pablogiraldo.grupoenlaces.service.RoleService;
import com.pablogiraldo.grupoenlaces.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/registry")
	public String registry() {
		return "registry";
	}

	@PostMapping("/register")
	public ModelAndView register(String username, String password) {
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isBlank(username)) {
			mv.setViewName("registry");
			mv.addObject("error", "El nombre no puede estar vacío");
			return mv;
		}
		if (StringUtils.isBlank(password)) {
			mv.setViewName("registry");
			mv.addObject("error", "La contraseña no puede estar vacía");
			return mv;
		}
		if (userService.existsByUsername(username)) {
			mv.setViewName("registry");
			mv.addObject("error", "Ese nombre de usuario ya existe");
			return mv;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		Role roleUser = roleService.getByName(RoleName.ROLE_USER).get();
		List<Role> roles = new ArrayList<>();
		roles.add(roleUser);
		user.setRoles(roles);
		userService.save(user);
		mv.setViewName("login");
		mv.addObject("registroOK", "Cuenta creada, " + user.getUsername() + ", ya puedes iniciar sesión");
		return mv;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/list");
		List<User> users = userService.list();
		List<User> usersFiltered = new ArrayList<>();
		for (User user : users) {
			boolean isEditor = false;
			for (Role role : user.getRoles()) {
				if (role.getName().equals(RoleName.ROLE_EDITOR)) {
					isEditor = true;
				}
			}
			if (!isEditor && user.getId() != 1) {
				usersFiltered.add(user);
			}
		}
		mv.addObject("users", usersFiltered);
		return mv;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/editorslist")
	public ModelAndView editorslist() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/editorslist");
		List<User> users = userService.list();
		List<User> usersFiltered = new ArrayList<>();
		for (User user : users) {
			boolean isEditor = false;
			for (Role role : user.getRoles()) {
				if (role.getName().equals(RoleName.ROLE_EDITOR)) {
					isEditor = true;
				}
			}
			if (isEditor) {
				usersFiltered.add(user);
			}
		}
		mv.addObject("users", usersFiltered);
		return mv;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") long id) {
		if (!userService.existsById(id))
			return new ModelAndView("redirect:/user/list");
		User user = userService.getById(id).get();
		ModelAndView mv = new ModelAndView("user/detail");
		mv.addObject("user", user);
		return mv;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/editordetail/{id}")
	public ModelAndView editordetail(@PathVariable("id") long id) {
		if (!userService.existsById(id))
			return new ModelAndView("redirect:/user/editorslist");
		User user = userService.getById(id).get();
		ModelAndView mv = new ModelAndView("user/editordetail");
		mv.addObject("user", user);
		return mv;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") long id) {
		if (!userService.existsById(id)) {
			return new ModelAndView("redirect:/user/list");
		}
		User user = userService.getById(id).get();
		ModelAndView mv = new ModelAndView("user/edit");
		mv.addObject("user", user);
		return mv;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/editormodify/{id}")
	public ModelAndView editormodify(@PathVariable("id") long id) {
		if (!userService.existsById(id)) {
			return new ModelAndView("redirect:/user/list");
		}

		User user = userService.getById(id).get();
		ModelAndView mv = new ModelAndView("user/editormodify");
		mv.addObject("user", user);
		return mv;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/update")
	public ModelAndView update(@RequestParam int id, @RequestParam(defaultValue = "") String role) {

		if (!userService.existsById(id)) {
			return new ModelAndView("redirect:/user/list");
		}

		User user = userService.getById(id).get();

		if (role.equals("ROLE_EDITOR")) {
			Role roleUser = roleService.getByName(RoleName.ROLE_USER).get();
			Role roleEditor = roleService.getByName(RoleName.ROLE_EDITOR).get();

			List<Role> roles = new ArrayList<>();
			roles.add(roleUser);
			roles.add(roleEditor);
			user.setRoles(roles);
			userService.save(user);

		} else {
			return new ModelAndView("redirect:/user/list");
		}
		userService.save(user);
		return new ModelAndView("redirect:/user/list");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/editorupdate")
	public ModelAndView editorupdate(@RequestParam long id, @RequestParam(defaultValue = "") String role) {
		if (!userService.existsById(id)) {
			return new ModelAndView("redirect:/user/editorslist");
		}

		User user = userService.getById(id).get();

		if (role.equals("ROLE_USUARIO")) {
			Role roleUser = roleService.getByName(RoleName.ROLE_USER).get();

			List<Role> roles = new ArrayList<>();
			roles.add(roleUser);
			user.setRoles(roles);
			userService.save(user);

		} else {
			return new ModelAndView("redirect:/user/editorslist");
		}
		userService.save(user);
		return new ModelAndView("redirect:/user/editorslist");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") long id) {
		if (userService.existsById(id)) {
			userService.delete(id);
			return new ModelAndView("redirect:/user/list");
		}
		return null;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/editordelete/{id}")
	public ModelAndView editordelete(@PathVariable("id") int id) {
		if (userService.existsById(id)) {
			userService.delete(id);
			return new ModelAndView("redirect:/user/editorslist");
		}
		return null;
	}
}
