package org.koreait.repositories;

import org.koreait.entities.Categories;
import org.koreait.entities.Products;
import org.koreait.entities.QCategories;
import org.koreait.entities.QUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.ArrayList;
import java.util.List;

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

	default List<String> getAllCateNm(){
		List<Categories> categoriesList = this.findAll();
		List<String> cateNmList = new ArrayList<>();
		for(Categories categories : categoriesList){
			cateNmList.add(categories.getCateNm());
		}
		return cateNmList;
	}
}
