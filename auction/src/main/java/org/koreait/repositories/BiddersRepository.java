package org.koreait.repositories;

import org.koreait.entities.Bidders;
import org.koreait.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BiddersRepository extends JpaRepository<Bidders, Long>, QuerydslPredicateExecutor {

}
