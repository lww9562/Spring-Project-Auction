package org.koreait.controllers.users;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.EmailValidator;
import org.koreait.commons.validators.MobileValidator;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, MobileValidator, EmailValidator {
	private final UsersRepository repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return JoinForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()){	//Bean Validation 기본 에러 검출
			return;
		}
		JoinForm joinForm = (JoinForm) target;

		String userId = joinForm.getUserId();		// 1. userId 중복 가입 검증
		String userPw = joinForm.getUserPw();		// 2. 비밀번호 & 비밀번호 확인란 일치여부 검증
		String userPwRe = joinForm.getUserPwRe();
		String mobile = joinForm.getMobile();		// 3. 휴대전화번호 형식 검증
		String email = joinForm.getEmail();			// 4. 이메일 형식 검증

		//1
		if(repository.userAlreadyExists(userId)){
			errors.rejectValue("userId", "Validation.Duplicate.userId");
		}

		//2
		if(!userPw.equals(userPwRe)){
			errors.rejectValue("userPwRe", "Validation.incorrect.userPwRe");
		}

		//3
		if(mobile != null && !mobile.isBlank()){
			if(!mobileCheck(mobile)){
				errors.rejectValue("mobile", "Validation.mobile");
			}

			mobile = mobile.replaceAll("\\D", "");
			joinForm.setMobile(mobile);
		}

		//4
		if(email != null && !email.isBlank()){
			if(!emailCheck(email)){
				errors.rejectValue("email", "Validation.email");
			}
		}
	}
}
