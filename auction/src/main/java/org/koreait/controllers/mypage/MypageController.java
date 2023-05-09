package org.koreait.controllers.mypage;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.users.UserInfo;
import org.koreait.controllers.users.UserInfoService;
import org.koreait.entities.Bidders;
import org.koreait.entities.Products;
import org.koreait.entities.Sellers;
import org.koreait.entities.Users;



import org.koreait.models.user.RequestMoneyService;

import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	private final RequestMoneyService requestMoneyService;

	//유저 정보
	@GetMapping
	public String index(@AuthenticationPrincipal UserInfo userinfo, Model model){

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails)principal;

		Users users = usersRepository.findByUserId(user.getUsername());
		//유저의 money 불러오기 위해 사용
		model.addAttribute("user", users);
		//유저의 정보
		model.addAttribute("userinfo",userinfo);

//		//판매 내역
//		Sellers sellers = users.getSeller();
//		List<Products> sellProducts = sellers.getSellProducts();
//
//		model.addAttribute("sellProducts", sellProducts);
//
//		//입찰 내역
//		Bidders bidders = users.getBidder();
//
//		List<Products> bidProducts = bidders.getProductList();
//
//		model.addAttribute("bidProducts",bidProducts);
//
//		// 최신 BidProducts 찾기


		//적당히 활용할 것
		return "mypage/index";
	}
	//판매내역
	@GetMapping("/sellerList")
	public String sellerList(@AuthenticationPrincipal UserInfo userinfo, Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails)principal;
		Users users = usersRepository.findByUserId(user.getUsername());

		Sellers sellers = users.getSeller();
		List<Products> sellProducts = sellers.getSellProducts();

		model.addAttribute("sellProducts", sellProducts);
		return "mypage/sellerList";
	}
	//구매내역
	@GetMapping("/bidderList")
	public String bidderList(@AuthenticationPrincipal UserInfo userinfo, Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails)principal;
		Users users = usersRepository.findByUserId(user.getUsername());

		Bidders bidders = users.getBidder();

		List<Products> bidProducts = bidders.getProductList();

		model.addAttribute("bidProducts",bidProducts);

		return "mypage/bidderList";
	}






	@GetMapping("/requestMoney")
	public String saveMoney(Model model){

		RequestMoneyForm requestMoneyForm = new RequestMoneyForm();
		model.addAttribute("requestMoneyForm",requestMoneyForm);

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails)principal;
		String userId = user.getUsername();

		model.addAttribute("Id" , userId);


		return "mypage/requestMoney";
	}



	@PostMapping("/requestMoney")
	public String saveMoneyPs(RequestMoneyForm requestMoneyForm , Model model){

		requestMoneyService.saveMoney(requestMoneyForm);

		return "redirect:/mypage";
	}




}
