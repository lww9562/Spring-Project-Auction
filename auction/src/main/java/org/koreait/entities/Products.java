package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor@AllArgsConstructor
@Data
//Products 클래스는 판매물품과 판매게시글 이라는 두가지 개념을 같이 가지고 있음
public class Products extends BaseUserEntity{
    //작성자 및 수정자를 파악하기 위해, 좀 더 새부적인 BaseUserEntity를 상속받도록 변경할게요
    @Id @GeneratedValue
    private Long id;            // 판매 물품 번호
    @Column(length = 100, nullable = false)
    private String prSubject;   // 판매 물품 게시글 제목
    @Column(nullable = false)
    private String prContent;   // 판매 물품 게시글 내용
    @Column(nullable = false)
    private Long startPrice;    // 경매 시작 가격

    @Column(nullable = false)
    private Long risingPrice;   // 경매물품 인상될 입찰가
    @Column(nullable = false)
    private Long baroPrice;     // 즉시 구매가

    private Long endPrice;      //최종 가격

    //private String userNm;      //작성자 - 해당 부분은 아래 sellerId로 변경해둘게요

    @OneToMany(mappedBy="bidderNo")
    //하나의 판매물품에 대해서는 여러 구매자(입찰자)가 존재할 수 있으므로, @OneToMany 매핑
    private List<Bidders> bidders = new ArrayList<>();

    @ManyToOne
    //하나의 Sellers는 여러 판매 물품을 등록할 수  있으므로, @ManyToOne 매핑
    @JoinColumn(name="sellerNo")
    private Sellers sellers;



//    private String category;
}
