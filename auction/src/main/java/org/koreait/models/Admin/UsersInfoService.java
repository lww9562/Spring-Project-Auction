package org.koreait.models.Admin;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersInfoService {
	private final UsersRepository usersRepository;

	public void getList(List<Users> userList, Errors errors){


	}
}
