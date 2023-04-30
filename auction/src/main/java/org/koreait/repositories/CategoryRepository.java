package org.koreait.repositories;

import org.koreait.entities.Categories;
import org.koreait.entities.Products;
import org.koreait.entities.QCategories;
import org.koreait.entities.QUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CategoryRepository extends JpaRepository<Categories, Long>, QuerydslPredicateExecutor {
	default boolean exists(Long cateId) {
		QCategories category = QCategories.categories;
		return exists(category.cateId.eq(cateId));
	}

	default boolean categoryAlreadyExists(String cateNm){
		QCategories categories = QCategories.categories;

		boolean exists = exists(categories.cateNm.eq(cateNm));

		return exists;
	}
}
