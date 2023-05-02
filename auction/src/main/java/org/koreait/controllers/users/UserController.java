package org.koreait.controllers.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.user.UserJoinValidator;
import org.koreait.models.user.UserJoinService;
import org.koreait.repositories.UsersRepository;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserJoinValidator validator;

	private final UserJoinService saveService;
	private final UserInfoService infoService;

	private final UsersRepository usersRepository;

	@GetMapping("/join")
	public String join(Model model) {
		JoinForm joinForm = new JoinForm();
		model.addAttribute("joinForm", joinForm);

		return "user/join";
	}

	@PostMapping("/join")
	public String joinPs(@Valid JoinForm joinForm, Errors errors){
		validator.validate(joinForm, errors);

		if(errors.hasErrors()){
			return "user/join";
		}

		saveService.save(joinForm);

		return "redirect:/user/login";
	}

	//테스트 유저 생성 (user01, user02) S
	@GetMapping("/join/test")
	public String joinTest() {
		JoinForm joinForm = new JoinForm();
		joinForm.setUserId("user01");
		joinForm.setUserPw("12345678");
		joinForm.setUserPwRe("12345678");
		joinForm.setUserNm("사용자01");
		joinForm.setEmail("user01@test.org");
		joinForm.setMobile("010-0000-0000");
		joinForm.setAgree(true);
		saveService.save(joinForm);

		JoinForm joinForm2 = new JoinForm();
		joinForm2.setUserId("user02");
		joinForm2.setUserPw("12345678");
		joinForm2.setUserPwRe("12345678");
		joinForm2.setUserNm("사용자02");
		joinForm2.setEmail("user02@test.org");
		joinForm2.setMobile("010-0000-0000");
		joinForm2.setAgree(true);
		saveService.save(joinForm2);

		return "user/login";
	}

	@GetMapping("/admin/{userId}")
	public String setAdmin(@PathVariable("userId") String userId) throws Exception{
		usersRepository.setAdmin(userId);
		return "user/login";
	}
	//테스트 유저 생성 E

	@GetMapping("/login")
	public String login(@CookieValue(required = false)String saveId, Model model){

		if(saveId != null){
			model.addAttribute("userId", saveId);
			model.addAttribute("saveId", saveId);
		}
		AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
		if (trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
			return "user/login";
		}
		else {
			return "redirect:/main";
		}


	}

}
