package org.koreait.models.Admin;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyUpdateService {

	private final UsersRepository repository;
	public void update(Long userNo, Long money){

		Users user = repository.findById(userNo).orElse(null);

		user.setMoney(money);

		repository.saveAndFlush(user);

	}
}
