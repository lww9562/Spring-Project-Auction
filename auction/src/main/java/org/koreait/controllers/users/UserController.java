package org.koreait.controllers.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.user.UserJoinValidator;
import org.koreait.models.user.UserJoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserJoinValidator validator;

	private final UserJoinService saveService;

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
		return "user/login";
	}
}
