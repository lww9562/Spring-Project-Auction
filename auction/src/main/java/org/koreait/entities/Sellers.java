package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @Builder @Entity
@NoArgsConstructor @AllArgsConstructor
public class Sellers {
	@Id
	@GeneratedValue
	private Long sellerNo;

	@OneToOne(mappedBy="seller")
	private Users user;

	@OneToMany(mappedBy="sellers")
	private List<Products> sellProducts = new ArrayList<>();

	//추후에 추가될 정보가 존재할 수 있음
}
