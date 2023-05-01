package org.koreait.restcontrollers.admin;

import lombok.RequiredArgsConstructor;
import org.koreait.models.Admin.MoneyUpdateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminAjaxController")
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AjaxController {
	private final MoneyUpdateService service;

	@GetMapping("/money")
	public String userMoneyUpdate(Long userNo, Long money) {
		System.out.printf("userNo=%d, money=%d%n", userNo, money);

		service.update(userNo, money);

		return "success";
	}
}
