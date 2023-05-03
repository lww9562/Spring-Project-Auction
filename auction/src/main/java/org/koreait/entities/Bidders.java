package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @Builder @Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bidders {
	@Id
	@GeneratedValue
	private Long bidderNo;

	@OneToOne(mappedBy="bidder")
	@ToString.Exclude
	private Users user;

	@ManyToMany(fetch = FetchType.LAZY)
	@ToString.Exclude
	@JoinTable(
			name = "BIDDER_PRODUCT_TABLE",
			joinColumns = @JoinColumn(name = "bidder_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Products> productList = new ArrayList<>();
	//추후에 추가될 정보가 존재할 수 있음
}
