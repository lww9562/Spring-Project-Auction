package org.koreait.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.koreait.controllers")
public class CommonController {
	@ExceptionHandler(Exception.class)
	public String errorHandler(Exception e, Model model){
		System.out.println("ExceptionHandler!!!");
		e.printStackTrace();
		model.addAttribute("messages", e.getMessage());
		return "error/common";
	}
}
