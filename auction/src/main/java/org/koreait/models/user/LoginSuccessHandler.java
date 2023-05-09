package org.koreait.models.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.koreait.controllers.users.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {	//로그인 성공 시 유입되는 핸들러

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		//1. 실패 시 저장되었던 세션 지우기(userId, userPw)
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userPw");
		session.removeAttribute("field");
		session.removeAttribute("message");

		//2. 아이디 저장 쿠키
		String saveId = request.getParameter("saveId");
		String userId = request.getParameter("userId");

		Cookie cookie = new Cookie("saveId", userId);

		if(saveId == null){
			cookie.setMaxAge(0);
		} else {
			cookie.setMaxAge(60*60*24*30);	//30일 쿠키 저장
		}
		response.addCookie(cookie);

		UserInfo userInfo = (UserInfo) authentication.getPrincipal();
		session.setAttribute("userInfo", userInfo);

		//3. 성공시 이동 URL
		String url = request.getContextPath()+"/main";	// contextPath를 main페이지로 둘 것
		response.sendRedirect(url);


	}
}
