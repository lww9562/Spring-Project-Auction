package org.koreait.controllers.mypage;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.users.UserInfo;
import org.koreait.controllers.users.UserInfoService;
import org.koreait.entities.Products;
import org.koreait.repositories.ProductRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

	private final ProductRepository productRepository;
	@GetMapping
	public String index(@AuthenticationPrincipal UserInfo userinfo, Model model){
		List<Products> productsList = productRepository.findAll();

		model.addAttribute("userinfo",userinfo);
		model.addAttribute("productsList", productsList);

		//적당히 활용할 것
		return "mypage/index";
	}
}
