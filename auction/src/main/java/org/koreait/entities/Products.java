package org.koreait.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor@AllArgsConstructor
@Data
public class Products extends BaseEntity{
    @Id @GeneratedValue
    private Long id; //출고품 고유 번호
    @Column(length = 100, nullable = false)
    private String prSubject; //출고품 제목
    @Column(nullable = false)
    private String prContent; //출고품 내용
    @Column(nullable = false)
    private Long startPrice; //시작 가격

    @Column(nullable = false)
    private Long risingPrice; //가격 인상 범위
    @Column(nullable = false)
    private Long baroPrice; // 즉시 구매가

    private Long endPrice; //최종 가격
    private String userNm; //작성자

//    private String category;

}
