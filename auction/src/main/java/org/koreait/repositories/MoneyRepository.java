package org.koreait.repositories;

import org.koreait.entities.Products;
import org.koreait.entities.RequestMoney;
import org.koreait.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MoneyRepository extends JpaRepository<RequestMoney,Long>, QuerydslPredicateExecutor<RequestMoney> {

    RequestMoney findByUserId(String userId);
}
