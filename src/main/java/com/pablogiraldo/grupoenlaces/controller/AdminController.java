package com.pablogiraldo.grupoenlaces.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("")
	public String admin() {
		return "admin";
	}
}
