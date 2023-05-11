package org.koreait.controllers.introduction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intro")
public class IntroductionController {
	@GetMapping
	public String intro(){

		return "introduction/index";
	}
}
