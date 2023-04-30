package org.koreait.repositories;

import org.koreait.entities.Bidders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BiddersRepository extends JpaRepository<Bidders, Long>, QuerydslPredicateExecutor {
}
