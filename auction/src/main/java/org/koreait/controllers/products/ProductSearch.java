package org.koreait.controllers.products;


import lombok.Data;

@Data
public class ProductSearch {
    private int page = 1;
    private int limit = 8;

    private String sort;
    private String cateNm;  //카테고리별 조회

    private String sopt; // 검색 항목

    private String skey; // 검색 항목 키워드
}
