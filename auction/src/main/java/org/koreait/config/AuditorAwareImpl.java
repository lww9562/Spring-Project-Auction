package org.koreait.config;

import org.koreait.controllers.users.UserInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {	//수정자

	@Override
	public Optional<String> getCurrentAuditor() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*
			principal → String 				: 비회원(Anonymous)
			principal → UserDetails 구현체 	: 회원
		 */
		String userId = null;

		if(principal instanceof UserInfo){
			UserInfo userInfo = (UserInfo)principal;
			userId = userInfo.getUserId();
		}

		return Optional.ofNullable(userId);
	}
}
