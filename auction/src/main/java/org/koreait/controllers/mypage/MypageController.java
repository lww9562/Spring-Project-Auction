package org.koreait.controllers.mypage;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.users.UserInfo;
import org.koreait.controllers.users.UserInfoService;
import org.koreait.entities.Bidders;
import org.koreait.entities.Products;
import org.koreait.entities.Sellers;
import org.koreait.entities.Users;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

	private final ProductRepository productRepository;
	private final UsersRepository usersRepository;
	@GetMapping
	public String index(@AuthenticationPrincipal UserInfo userinfo, Model model){

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails)principal;

		Users users = usersRepository.findByUserId(user.getUsername());
		//유저의 money 불러오기 위해 사용
		model.addAttribute("user", users);
		//유저의 정보
		model.addAttribute("userinfo",userinfo);

		//판매 내역
		Sellers sellers = users.getSeller();
		List<Products> sellProducts = sellers.getSellProducts();

		model.addAttribute("seller", sellers);

		//입찰 내역
		Bidders bidders = users.getBidder();

		List<Products> bidProducts = bidders.getProductList();

		// 최신 BidProducts 찾기

		model.addAttribute("latestBidProduct", latestBidProduct);


		//적당히 활용할 것
		return "mypage/index";
	}
}
