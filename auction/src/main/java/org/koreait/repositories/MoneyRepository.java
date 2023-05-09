package org.koreait.repositories;

import org.koreait.entities.Products;
import org.koreait.entities.RequestMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MoneyRepository extends JpaRepository<RequestMoney,Long>, QuerydslPredicateExecutor<RequestMoney> {

}
