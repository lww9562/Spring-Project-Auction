package org.koreait.models.user;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.EmailValidator;
import org.koreait.commons.validators.MobileValidator;
import org.koreait.commons.validators.RequiredValidator;
import org.koreait.controllers.users.JoinForm;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserJoinValidator implements Validator, MobileValidator, EmailValidator, RequiredValidator {
	private final UsersRepository repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return JoinForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target){
		validate(target, null);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors != null && errors.hasErrors()){	//Bean Validation 기본 에러 검출
			return;
		}
		JoinForm joinForm = (JoinForm) target;

		nullCheck(joinForm, new JoinValidationException("잘못된 접근입니다."));

		String userId = joinForm.getUserId();		// 1. userId 중복 가입 검증
		String userPw = joinForm.getUserPw();		// 2. 비밀번호 & 비밀번호 확인란 일치여부 검증
		String userPwRe = joinForm.getUserPwRe();
		String userNm = joinForm.getUserNm();
		String mobile = joinForm.getMobile();		// 3. 휴대전화번호 형식 검증
		String email = joinForm.getEmail();			// 4. 이메일 형식 검증

		requiredCheck(userId, new JoinValidationException("아이디을 입력하세요."));
		requiredCheck(userPw, new JoinValidationException("비밀번호를 입력하세요."));
		requiredCheck(userPwRe, new JoinValidationException("비밀번호 확인란을 입력하세요."));
		requiredCheck(userNm, new JoinValidationException("이름을 입력하세요."));


		//1
		if(repository.userAlreadyExists(userId)){
			errors.rejectValue("userId", "Validation.Duplicate.userId");
			//throw new JoinValidationException("이미 가입된 아이디입니다.");
		}

		//2
		if(!userPw.equals(userPwRe)){
			errors.rejectValue("userPwRe", "Validation.incorrect.userPwRe");
			//throw new JoinValidationException("비밀번호와 비밀번호 확인란이 일치하지 않습니다.");
		}

		//3
		if(mobile != null && !mobile.isBlank()){
			if(!mobileCheck(mobile)){
				errors.rejectValue("mobile", "Validation.mobile");
				throw new JoinValidationException("올바른 휴대전화번호 형식이 아닙니다.");
			}

			mobile = mobile.replaceAll("\\D", "");
			joinForm.setMobile(mobile);
		}

		//4
		if(email != null && !email.isBlank()){
			if(!emailCheck(email)){
				errors.rejectValue("email", "Validation.email");
				throw new JoinValidationException("올바른 이메일 형식이 아닙니다.");
			}
		}

	}

}
