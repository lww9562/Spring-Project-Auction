package org.koreait.controllers.admins;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	private final UsersRepository usersRepository;
	@GetMapping
	public String admin(){

		return "admin/main/index";
	}

	@GetMapping("user")
	public String usersInfo(Model model){
		List<Users> userList = usersRepository.findAll();

		model.addAttribute("userList", userList);

		return "admin/user/list";
	}
}
