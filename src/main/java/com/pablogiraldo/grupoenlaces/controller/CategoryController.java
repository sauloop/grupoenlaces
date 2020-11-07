package com.pablogiraldo.grupoenlaces.controller;

import java.util.List;
//import java.util.Optional;
//
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pablogiraldo.grupoenlaces.entity.Category;
import com.pablogiraldo.grupoenlaces.service.CategoryService;

//import com.tutorial.crud.entity.Article;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public String list(Model model) {
		List<Category> categories = categoryService.list();

		model.addAttribute("categories", categories);

		return "category/list";
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("/create")
	public String create(Model model) {
		return "category/create";
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@PostMapping("/save")
	public ModelAndView save(@RequestParam String name) {
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isBlank(name)) {
			mv.setViewName("category/create");
			mv.addObject("error", "El nombre no puede estar vacío");
			return mv;
		}

		if (categoryService.existsByName(name)) {
			mv.setViewName("category/create");
			mv.addObject("error", "Esa categoría ya existe");
			return mv;
		}
		Category category = new Category(name);
		categoryService.save(category);
		mv.setViewName("redirect:/category/list");
		return mv;
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		if (!categoryService.existsById(id)) {
			return new ModelAndView("redirect:/categoria/lista");
		}

		Category category = categoryService.getById(id).get();
		ModelAndView mv = new ModelAndView("category/edit");
		mv.addObject("category", category);
		return mv;
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@PostMapping("update")
	public ModelAndView update(@RequestParam int id, @RequestParam String name) {
		if (!categoryService.existsById(id)) {
			return new ModelAndView("redirect:/category/list");
		}

		ModelAndView mv = new ModelAndView();
		Category category = categoryService.getById(id).get();
		if (StringUtils.isBlank(name)) {
			mv.setViewName("category/edit");
			mv.addObject("category", category);
			mv.addObject("error", "El nombre no puede estar vacío");
			return mv;
		}

		if (categoryService.existsByName(name) && categoryService.getByName(name).get().getId() != id) {
			mv.setViewName("category/edit");
			mv.addObject("error", "Ese nombre ya existe");
			mv.addObject("category", category);
			return mv;
		}

		category.setName(name);
		categoryService.save(category);
		return new ModelAndView("redirect:/category/list");
	}

	@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		if (categoryService.existsById(id)) {
			categoryService.delete(id);
			return new ModelAndView("redirect:/category/list");
		}
		return null;
	}

}
