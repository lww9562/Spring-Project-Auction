package org.koreait.Users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controllers.users.JoinForm;
import org.koreait.models.user.JoinValidationException;
import org.koreait.models.user.UserJoinService;
import org.koreait.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class UserTest {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private UserJoinService joinService;

	@Autowired
	private MockMvc mockMvc;

	private JoinForm joinForm;


	@BeforeEach
	public void init(){
		joinForm = JoinForm.builder()
				.userId("user01")
				.userPw("12345678")
				.userPwRe("12345678")
				.userNm("사용자01")
				.agree(true)
				.build();
	}

	@Test
	@DisplayName("회원가입 성공 시 오류 없음")
	@WithMockUser("user01")
	void userJoinTest(){
		assertDoesNotThrow(()->{
			joinService.save(joinForm);
		});
	}

	@Test
	@DisplayName("joinForm이 null값일 때, 오류 메세지 출력")
	@WithMockUser("user01")
	void userJoinNullTest(){
		JoinValidationException exception = assertThrows(JoinValidationException.class, ()->{
			joinService.save(null);
		});

		assertTrue(exception.getMessage().contains("잘못된 접근입니다."));

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
	}

	//필수항목 체크
	@Test
	@DisplayName("필수항목 체크 - userId")
	@WithMockUser("user01")
	void userJoinNullTest1(){
		for(int i = 0; i < 2; i++){
			if(i == 0){
				joinForm.setUserId(null);
			} else{
				joinForm.setUserId(" ");
			}
			JoinValidationException exception = assertThrows(JoinValidationException.class, ()->{
				joinService.save(joinForm);
			});

			assertTrue(exception.getMessage().contains("아이디을 입력하세요."));
			assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
		}
	}

	@Test
	@DisplayName("필수항목 체크 - userPw")
	@WithMockUser("user01")
	void userJoinNullTest2(){
		for(int i = 0; i < 2; i++){
			if(i == 0){
				joinForm.setUserPw(null);
			} else{
				joinForm.setUserPw(" ");
			}
			JoinValidationException exception = assertThrows(JoinValidationException.class, ()->{
				joinService.save(joinForm);
			});

			assertTrue(exception.getMessage().contains("비밀번호를 입력하세요."));
			assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
		}
	}

	@Test
	@DisplayName("필수항목 체크 - userPwRe")
	@WithMockUser("user01")
	void userJoinNullTest3(){
		for(int i = 0; i < 2; i++){
			if(i == 0){
				joinForm.setUserPwRe(null);
			} else{
				joinForm.setUserPwRe(" ");
			}
			JoinValidationException exception = assertThrows(JoinValidationException.class, ()->{
				joinService.save(joinForm);
			});

			assertTrue(exception.getMessage().contains("비밀번호 확인란을 입력하세요."));
			assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
		}
	}

	@Test
	@DisplayName("필수항목 체크 - userNm")
	@WithMockUser("user01")
	void userJoinNullTest4(){
		for(int i = 0; i < 2; i++){
			if(i == 0){
				joinForm.setUserNm(null);
			} else{
				joinForm.setUserNm(" ");
			}
			JoinValidationException exception = assertThrows(JoinValidationException.class, ()->{
				joinService.save(joinForm);
			});

			assertTrue(exception.getMessage().contains("이름을 입력하세요."));
			assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
		}
	}


	//통합 테스트
	@Test
	@DisplayName("회원가입 성공 시 /user/login 으로 이동")
	@WithMockUser("user01")
	void joinSuccessRedirectTest() throws Exception {
		mockMvc.perform(post("/user/join")
				.param("userId", joinForm.getUserId())
				.param("userPw", joinForm.getUserPw())
				.param("userPwRe", joinForm.getUserPwRe())
				.param("userNm", joinForm.getUserNm())
				.param("agree", String.valueOf(joinForm.isAgree()))
						.with(csrf()))
				.andExpect(redirectedUrl("/user/login"));
	}

	@Test
	@DisplayName("id 중복확인 체크")
	@WithMockUser("user01")
	void duplicatedIdCheckTest() throws Exception {
		joinService.save(joinForm);
		System.out.println("여기서부터 본 테스트");

		mockMvc.perform(post("/user/join")
						.param("userId", joinForm.getUserId())
						.param("userPw", joinForm.getUserPw())
						.param("userPwRe", joinForm.getUserPwRe())
						.param("userNm", joinForm.getUserNm())
						.param("agree", String.valueOf(joinForm.isAgree()))
				)
				.andReturn().getModelAndView().getModel().containsValue("이미 가입된");
	}

	@Test
	@DisplayName("비밀번호 & 비밀번호 확인란 일치 테스트")
	void pwReCheckTest() throws Exception{
		joinForm.setUserPw("12345678");
		joinForm.setUserPwRe("23456789");

		String response = mockMvc.perform(post("/user/join")
						.param("userId", joinForm.getUserId())
						.param("userPw", joinForm.getUserPw())
						.param("userPwRe", joinForm.getUserPwRe())
						.param("userNm", joinForm.getUserNm())
						.param("agree", String.valueOf(joinForm.isAgree()))
				)
				.andReturn().getResponse().getContentAsString();
		response.contains("비밀번호 확인란이 일치하지 않습니다.");
	}
}
