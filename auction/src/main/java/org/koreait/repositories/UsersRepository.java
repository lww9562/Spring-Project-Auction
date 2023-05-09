package org.koreait.repositories;

import org.koreait.entities.QUsers;
import org.koreait.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Long>, QuerydslPredicateExecutor {
	Users findByUserId(String userId);

	default boolean userAlreadyExists(String userId){
		QUsers users = QUsers.users;

		boolean exists = exists(users.userId.eq(userId));

		return exists;
	}

	@Query(value = "UPDATE USERS u SET u.USERTYPE = 'ADMIN' WHERE u.USERID=:userId;", nativeQuery = true)
	void setAdmin(@Param("userId")String userId) throws Exception;
}
