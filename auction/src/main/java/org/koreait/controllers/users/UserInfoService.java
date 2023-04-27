package org.koreait.controllers.users;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {	// Spring Security - UserDetailsService
	private final UsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repository.findByUserId(username);	//아이디를 바탕으로 데이터 조회
		if(user == null){
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getUserType().toString()));
		//리스트 형태로 권한을 넣어준다.
		//user에서 가져온 권한을 toString으로 변환 후, SimpleGrantedAuthority를 생성, Collection 타입에 넣어줌
		//넣은 값을 UserInfo에 다시 넣어줌으로써 Spring Security에서 사용

		return UserInfo.builder()
				.userNo(user.getUserNo())
				.userId(user.getUserId())
				.userPw(user.getUserPw())
				.userNm(user.getUserNm())
				.email(user.getEmail())
				.mobile(user.getMobile())
				.authorities(authorities)
				.build();
	}
}
