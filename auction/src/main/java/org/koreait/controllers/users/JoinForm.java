package org.koreait.controllers.users;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.entities.Users;
import org.modelmapper.ModelMapper;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JoinForm {
	@NotBlank
	@Size(min=6)
	private String userId;

	@NotBlank
	@Size(min=8, max=16)
	private String userPw;
	@NotBlank
	private String userPwRe;

	@NotBlank
	private String userNm;

	@Email
	private String email;
	private String mobile;

	@AssertTrue
	private boolean agree;

	public static Users of(JoinForm joinForm){
		return new ModelMapper().map(joinForm, Users.class);
	}
}
