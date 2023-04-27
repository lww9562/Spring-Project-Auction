package org.koreait.controllers.commons;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")		// 에러 관련 컨트롤러로, ExceptionHandler를 통해 유입시킬 것
public class ErrorsController {
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/401")
	public String unauthorized(){
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return "error/401";
	}

	@RequestMapping("/404")
	public String notFound(){
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return "error/404";
	}
}
