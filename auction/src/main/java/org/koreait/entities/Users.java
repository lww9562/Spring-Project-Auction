package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.koreait.constants.UserType;

@Data @Builder @Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name="users", indexes ={
		@Index(name="idx_users_id", columnList = "userId"),
		@Index(name="idx_user_type", columnList = "userType")
})
public class Users extends BaseEntity {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long userNo;			//회원번호

	@Column(length=40, unique = true, nullable = false)
	private String userId;			//아이디

	@Column(length=80, nullable = false)
	private String userPw;			//비밀번호

	@Column(length=45, nullable = false)
	private String userNm;			//이름

	@Column(length=100)
	private String email;			//이메일

	@Column(length=11)
	private String mobile;			//전화번호

	@Column(length=20)
	private Long money=0L;

	@Enumerated(value = EnumType.STRING)
	@Column(length=20, nullable = false)
	private UserType userType = UserType.USER;	//권한 - 기본값 : USER

	@OneToOne
	@JoinColumn
	@ToString.Exclude
	private Bidders bidder;

	@OneToOne
	@JoinColumn
	@ToString.Exclude
	private Sellers seller;
}
