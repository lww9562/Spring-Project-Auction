package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

@Data @Builder @Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bidders {
	@Id
	@GeneratedValue
	private Long bidderNo;

	@OneToOne(mappedBy="bidder")
	private Users user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="productNo")
	@ToString.Exclude
	private Products bidProduct;

	//추후에 추가될 정보가 존재할 수 있음
}
