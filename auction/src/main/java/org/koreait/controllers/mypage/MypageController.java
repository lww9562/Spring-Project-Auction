package org.koreait.controllers.mypage;

import org.koreait.controllers.users.UserInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@GetMapping
	public String index(@AuthenticationPrincipal UserInfo userinfo){
		//적당히 활용할 것
		return "mypage/index";
	}
}
