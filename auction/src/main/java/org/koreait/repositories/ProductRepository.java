package org.koreait.repositories;

import org.koreait.entities.Products;
import org.koreait.entities.QProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Products,Long>, QuerydslPredicateExecutor {

    default boolean exists(Long id){
        QProducts products = QProducts.products;
        return exists(products.id.eq(id));
    }
}
