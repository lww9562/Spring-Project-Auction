package org.koreait.repositories;

import com.querydsl.core.BooleanBuilder;
import org.koreait.controllers.products.ProductSearch;
import org.koreait.entities.Products;
import org.koreait.entities.QProducts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Order.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Long>, QuerydslPredicateExecutor<Products> {

    default boolean exists(Long id){
        QProducts products = QProducts.products;
        return exists(products.id.eq(id));

    }

    /**
     * 상품 목록 조회
     * @param search
     * @return
     */
    default Page<Products> getProducts(ProductSearch search) {
        
        BooleanBuilder andBuilder = new BooleanBuilder();
        QProducts products = QProducts.products;
        /** 추가 검색 조건 처리 S */
        // 예로 하나 만들어 드리겠습니다~ 필요에 따라 더 추가하시면 됩니다.
        String sopt = search.getSopt();
        String skey = search.getSkey();
        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            if (sopt.equals("ALL")) { // 통합 검색 - 물품 제목, 물품 내용 중에서
                BooleanBuilder orBuilder = new BooleanBuilder();
                orBuilder.or(products.prSubject.contains(skey))
                        .or(products.prContent.contains(skey));
                andBuilder.and(orBuilder);
            } else if (sopt.equals("prSubject")) { // 제목에서 검색
                andBuilder.and(products.prSubject.contains(skey));
            }
        }
        /** 추가 검색 조건 처리 E */


        /** 정렬 처리 S */
        String sort = search.getSort();
        int page = search.getPage();
        int limit = search.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;
        Sort sort2 = Sort.by(desc("regDt"));
        if (sort != null) {
            String[] sorts = sort.split("\\s+");
            if (sorts[1].toUpperCase().equals("DESC")) {
                sort2 = Sort.by(desc(sorts[0]));
            } else {
                sort2 = Sort.by(asc(sorts[0]));
            }
        }
        /** 정렬 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, sort2);
        Page<Products> productList = findAll(andBuilder, pageable);

        return productList;
    }
}
