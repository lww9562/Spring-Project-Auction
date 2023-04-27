package org.koreait.models.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginFailureHandler implements AuthenticationFailureHandler {	//로그인 실패 시 유입되는 핸들러

	//로그인 항목 검증과 더불어
	//session에 속성값으로써 값을 저장하여 다시 읽어올 수 있도록 한다.
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		ResourceBundle bundle = ResourceBundle.getBundle("messages.validations");
		//메세지를 가져온다
		HttpSession session = request.getSession();

		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		try{
			if(userId == null || userId.isBlank()){
				System.out.println("userId == null");
				throw new LoginValidationException(bundle.getString("NotBlank.userId"), "userId");
			}

			if(userPw == null || userPw.isBlank()){
				throw new LoginValidationException(bundle.getString("NotBlank.userPw"), "userPw");
			}

			throw new LoginValidationException(bundle.getString("Validation.incorrect.login"), "global");

		} catch(LoginValidationException e){
			String field = e.getField();
			String message = e.getMessage();

			session.setAttribute("field", field);
			session.setAttribute("message", message);
		}

		session.setAttribute("userId", userId);			//페이지가 다시 로딩될 시, session에 저장해둔 값을 불러오기 위함
		session.setAttribute("userPw", userPw);

		String url = request.getContextPath() + "/user/login";
		response.sendRedirect(url);				//redirect
	}
}
