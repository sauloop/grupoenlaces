package com.pablogiraldo.grupoenlaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pablogiraldo.grupoenlaces.entity.Link;
import com.pablogiraldo.grupoenlaces.service.LinkService;
import com.pablogiraldo.grupoenlaces.util.RenderizadorPaginas;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private LinkService linkService;

	@GetMapping(value = { "", "/home" })
	public String home(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable linkPageable = PageRequest.of(page, 4);

		Page<Link> links = linkService.list(linkPageable);

		RenderizadorPaginas<Link> renderizadorPaginas = new RenderizadorPaginas<Link>("/", links);

		model.addAttribute("renpag", renderizadorPaginas);
		model.addAttribute("links", links);

		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/forbidden")
	public String forbidden() {
		return "forbidden";
	}
}
