package org.koreait.controllers.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.user.UserJoinValidator;
import org.koreait.models.user.UserJoinService;
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
