package org.koreait.repositories;

import org.koreait.entities.Sellers;
import org.koreait.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SellersRepository extends JpaRepository<Sellers, Long>, QuerydslPredicateExecutor {
	Sellers findByUser(Users users);
}
