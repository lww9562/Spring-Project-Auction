package org.koreait.controllers.admins;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Products;
import org.koreait.entities.RequestMoney;
import org.koreait.entities.Users;
import org.koreait.models.user.MoneyListService;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	private final UsersRepository usersRepository;
	private final ProductRepository productRepository;
	private final MoneyListService moneyListService;

	@GetMapping
	public String admin(){

		return "admin/main/index";
	}

	@GetMapping("/user")
	public String usersInfo(Model model){
		List<Users> userList = usersRepository.findAll();

		model.addAttribute("userList", userList);
		model.addAttribute("addScript", new String[] { "admin/user"});
		return "admin/user/list";
	}

	@GetMapping("/product/list")
	public String productsInfo(Model model){
		List<Products> productsList = productRepository.findAll();
		Collections.sort(productsList);

		model.addAttribute("productsList", productsList);

		return "admin/product/list";
	}
	@GetMapping("/money")
	public String requestMoney(Model model){
		List<Users> userList = usersRepository.findAll();

		model.addAttribute("userList", userList);

		List<RequestMoney> requestMonies = moneyListService.gets();
		model.addAttribute("requestMonies",requestMonies);
		return "admin/money/requestMoney";
	}
}
