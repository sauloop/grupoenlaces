package com.pablogiraldo.grupoenlaces.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pablogiraldo.grupoenlaces.entity.Link;
import com.pablogiraldo.grupoenlaces.service.LinkService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private LinkService linkService;

	@GetMapping("")
	public String listArticles(Model model) {
		List<Link> links = linkService.list();

		model.addAttribute("links", links);

		return "home";
	}

//    @GetMapping(value = {"", "/index"})
//    public String index(){
//        return "home";
//    }

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/forbidden")
	public String forbidden() {
		return "forbidden";
	}
}
