package org.koreait.controllers.admins.categories;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.Category.CategorySaveService;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryRepository repository;
	private final CategorySaveService service;

	@GetMapping
	public String index(){
		return "admin/category/index";
	}

	//카테고리 등록
	@GetMapping("/register")
	public String register(Model model){
		CategoryForm categoryForm = new CategoryForm();
		model.addAttribute("categoryForm", categoryForm);
		return "admin/category/register";
	}

	@PostMapping
	public String register(@Valid CategoryForm categoryForm, Errors errors){
		try{
			service.save(categoryForm, errors);
		} catch(Exception e){
			errors.rejectValue("", "");
		}

		if(errors.hasErrors()){
			return "admin/category/register";
		}

		return "redirect:/admin/category";
	}
}
