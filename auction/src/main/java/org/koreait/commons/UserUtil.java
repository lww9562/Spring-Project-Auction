package org.koreait.commons;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.koreait.constants.UserType;
import org.koreait.controllers.users.UserInfo;
import org.koreait.entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
	private final HttpSession session;

	private UserInfo getUser(){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		return userInfo;
	}

	private Users getEntity() {
		if(!isLogin())
			return null;

		return new ModelMapper().map(getUser(), Users.class);
	}

	public boolean isLogin() {
		return getUser() != null;
	}

	public boolean isAdmin() {
		Users users = getEntity();

		if(users != null && users.getUserType() == UserType.ADMIN) {
			return true;
		}
		return false;
	}

	public boolean isMine(String userId){
		return isLogin() && getUser().getUserId().equals(userId);
	}

}
