package org.koreait.models.user;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.users.JoinForm;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoinService {
	private final UsersRepository repository;
	private final PasswordEncoder passwordEncoder;

	public void save(JoinForm joinForm){
		Users user = JoinForm.of(joinForm);

		String hash = passwordEncoder.encode(joinForm.getUserPw());
		user.setUserPw(hash);

		repository.saveAndFlush(user);
	}
}
