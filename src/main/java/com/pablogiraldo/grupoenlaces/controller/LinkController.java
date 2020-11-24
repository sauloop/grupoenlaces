package com.pablogiraldo.grupoenlaces.controller;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Date;
import java.util.List;
//import java.util.Optional;
//import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pablogiraldo.grupoenlaces.entity.Category;
import com.pablogiraldo.grupoenlaces.entity.Link;
import com.pablogiraldo.grupoenlaces.service.CategoryService;
import com.pablogiraldo.grupoenlaces.service.LinkService;

@Controller
@RequestMapping("/link")
public class LinkController {

	@Autowired
	private LinkService linkService;

	@Autowired
	private CategoryService categoryService;

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("/list")
	public String list(Model model) {
		List<Link> links = linkService.list();

		model.addAttribute("links", links);

		return "link/list";
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("/create")
	public String create(Model model) {
		List<Category> categories = categoryService.list();
		model.addAttribute("categories", categories);
		return "link/create";
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@PostMapping("/save")
	public ModelAndView save(@RequestParam Category category, @RequestParam String title, @RequestParam String subtitle,
			@RequestParam String url) {
		List<Category> categories = categoryService.list();

		ModelAndView mv = new ModelAndView();
		if (StringUtils.isBlank(title)) {
			mv.setViewName("link/create");
			mv.addObject("categories", categories);
			mv.addObject("error", "El título no puede estar vacío");
			return mv;
		}

		if (linkService.existsByTitle(title)) {
			mv.setViewName("link/create");
			mv.addObject("categories", categories);
			mv.addObject("error", "Ese enlace ya existe");
			return mv;
		}
		Link link = new Link(title, subtitle, url, category);
		linkService.save(link);
		mv.setViewName("redirect:/link/list");
		return mv;
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") long id) {
		if (!linkService.existsById(id)) {
			return new ModelAndView("redirect:/link/list");
		}

		List<Category> categories = categoryService.list();
		Link link = linkService.getById(id).get();

		ModelAndView mv = new ModelAndView("link/edit");
		mv.addObject("categories", categories);
		mv.addObject("link", link);
		return mv;
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@PostMapping("/update")
	public ModelAndView update(@RequestParam long id, @RequestParam String title, @RequestParam String subtitle,
			@RequestParam String url, @RequestParam Category category) {
		if (!linkService.existsById(id)) {
			return new ModelAndView("redirect:/link/list");
		}

		ModelAndView mv = new ModelAndView();
		Link link = linkService.getById(id).get();
		if (StringUtils.isBlank(title)) {
			mv.setViewName("link/edit");
			mv.addObject("link", link);
			mv.addObject("error", "El título no puede estar vacío");
			return mv;
		}

		if (linkService.existsByTitle(title) && linkService.getByTitle(title).get().getId() != id) {
			mv.setViewName("link/edit");
			mv.addObject("error", "Ese título ya existe");
			mv.addObject("link", link);
			return mv;
		}

		link.setTitle(title);
		link.setSubtitle(subtitle);
		link.setUrl(url);
		link.setCategory(category);
		linkService.save(link);
		return new ModelAndView("redirect:/link/list");
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") long id) {
		if (linkService.existsById(id)) {
			linkService.delete(id);
			return new ModelAndView("redirect:/link/list");
		}
		return null;
	}

	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryService.list());
		model.addAttribute("links", new ArrayList<>());

		return "link/search";
	}

	@GetMapping("/searcher")
	public String searcher(@RequestParam String name, Model model, @ModelAttribute("category") Category category) {

		if (name == "") {

			return "redirect:/link/search";

		} else {
			Category cat = categoryService.getByName(name).get();
			List<Link> links = cat.getLinks();
			Collections.sort(links);

			model.addAttribute("categories", categoryService.list());
			model.addAttribute("links", links);

			return "link/search";
		}
	}
}
