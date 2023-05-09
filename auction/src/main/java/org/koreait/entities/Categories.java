package org.koreait.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
public class Categories extends BaseEntity implements Comparable<Categories>{
	@Id
	@Column(length=20, nullable = false)
	private Long cateId;					//카테고리 ID

	@Column(nullable = false)
	private boolean use;					//카테고리 사용여부

	@Column(length = 20, nullable = false, unique = true)
	private String cateNm;					//카테고리 이름

	private Long orderNo;					//카테고리 순서

	@OneToMany(mappedBy="categories")
	private List<Products> products;

	@Override
	public int compareTo(Categories o) {
		if(this.orderNo > o.orderNo)
			return 1;
		else if(this.orderNo == o.orderNo)
			return 0;
		else
			return -1;
	}
}
