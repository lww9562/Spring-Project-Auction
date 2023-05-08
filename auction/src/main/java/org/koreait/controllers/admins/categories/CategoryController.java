package org.koreait.controllers.admins.categories;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Bidders;
import org.koreait.entities.Categories;
import org.koreait.entities.Products;
import org.koreait.models.Category.CategoryListService;
import org.koreait.models.Category.CategorySaveService;
import org.koreait.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryRepository repository;
	private final CategorySaveService saveService;
	private final CategoryListService listService;


	//카테고리 등록
	@GetMapping("/register")
	public String register(Model model){
		CategoryForm categoryForm = new CategoryForm();
		model.addAttribute("categoryForm", categoryForm);

		List<Categories> categoriesList = listService.gets();
		model.addAttribute("categoriesList",categoriesList);
		return "admin/category/register";
	}
	

	@PostMapping
	public String register(@Valid CategoryForm categoryForm, Errors errors){
		try{
			saveService.save(categoryForm, errors);
		} catch(Exception e){
			errors.rejectValue("", "");
		}

		if(errors.hasErrors()){
			return "admin/category/register";
		}

		return "redirect:/admin/category";
	}

	@GetMapping("/list")
	public String list(Model model){
		List<Categories> categoriesList = listService.gets();

		model.addAttribute("categoriesList",categoriesList);

//		model.addAttribute("category",category);
		return "admin/category/list";
	}
//	@GetMapping("/update")
//	public String update(Model model){
//
//		CategoryForm categoryForm = new ModelMapper().map(category, CategoryForm.class);
//		model.addAttribute("categoryForm", categoryForm);
//		return "admin/category/update";
//	}
	@PostMapping("/list")
	public String listPs(Model model){

		List<Categories> categoriesList = listService.gets();
//		Categories categories = categoriesList.get()
		model.addAttribute("categoriesList",categoriesList);

		return "redirect:/admin/category/list";
	}
}
