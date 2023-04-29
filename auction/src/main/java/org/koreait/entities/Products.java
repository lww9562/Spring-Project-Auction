package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.ToOne;

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
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

}
