package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bidders {
	@Id
	@GeneratedValue
	private Long bidderNo;

	@OneToOne(mappedBy="bidder")
	private Users user;

	@ManyToOne
	@JoinColumn(name="productNo")
	private Products bidProduct;

	//추후에 추가될 정보가 존재할 수 있음
}
