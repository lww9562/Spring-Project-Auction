package org.koreait.models.user;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.users.JoinForm;
import org.koreait.entities.Bidders;
import org.koreait.entities.Sellers;
import org.koreait.entities.Users;
import org.koreait.repositories.BiddersRepository;
import org.koreait.repositories.SellersRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoinService {
	private final UsersRepository repository;
	private final SellersRepository sellersRepository;
	private final BiddersRepository biddersRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserJoinValidator validator;

	public void save(JoinForm joinForm){
		validator.validate(joinForm);

		Users user = JoinForm.of(joinForm);

		Sellers sellers = Sellers.builder()
				.user(user)
				.build();
		Bidders bidders = Bidders.builder()
				.user(user)
				.build();


		String hash = passwordEncoder.encode(joinForm.getUserPw());
		user.setUserPw(hash);

		repository.saveAndFlush(user);
		sellersRepository.saveAndFlush(sellers);
		biddersRepository.saveAndFlush(bidders);
	}
}
