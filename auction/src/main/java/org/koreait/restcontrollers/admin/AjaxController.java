package org.koreait.restcontrollers.admin;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.models.Admin.CategoryStatsUpdateService;
import org.koreait.models.Admin.MoneyUpdateService;
import org.koreait.models.Admin.ProductStatsUpdateService;
import org.koreait.repositories.MoneyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("AdminAjaxController")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AjaxController {
	private final MoneyUpdateService service;
	private final ProductStatsUpdateService productService;
	private final CategoryStatsUpdateService categoryService;

	@GetMapping("/user/money")
	public String userMoneyUpdate(Long userNo, Long money) {
		System.out.printf("userNo=%d, money=%d%n", userNo, money);

		service.update(userNo, money);

		return "success";
	}
	@GetMapping("/money/money")
	public String userMoneyUpdate2(String userId, Long money, Long id) {
		System.out.printf("userId=%s, money=%d%n, id=%d%n", userId, money, id);

		service.update(userId, money, id);

		return "success";
	}
	@GetMapping("/product")
	public String productStatsUpdate(Long productId, String stats) {
		System.out.printf("productId=%d, stats=%s", productId, stats);

		boolean change_stats = Boolean.parseBoolean(stats);
		productService.update(productId, change_stats);

		return "success";
	}

	@GetMapping("/category/update")
	public String categoryStatsUpdate(Long cateID, String stats) {
		System.out.printf("cateId=%d, stats=%s", cateID, stats);

		boolean change_stats = Boolean.parseBoolean(stats);
		categoryService.update(cateID, change_stats);

		return "success";
	}
}
