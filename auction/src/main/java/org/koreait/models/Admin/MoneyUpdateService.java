package org.koreait.models.Admin;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.RequestMoney;
import org.koreait.entities.Users;
import org.koreait.repositories.MoneyRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyUpdateService {

	private final UsersRepository repository;
	private final MoneyRepository moneyRepository;
	public void update(Long userNo, Long money){

		Users user = repository.findById(userNo).orElse(null);

		user.setMoney(money);

		repository.saveAndFlush(user);

	}
	public void update(String userId, Long money , Long id){
		Users user = repository.findByUserId(userId);
		user.setMoney(user.getMoney()+money);
		repository.saveAndFlush(user);

		RequestMoney requestMoney = moneyRepository.findById(id).orElse(null);
		moneyRepository.delete(requestMoney);
		moneyRepository.flush();
	}
}
