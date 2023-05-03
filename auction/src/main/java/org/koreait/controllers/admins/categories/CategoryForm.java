package org.koreait.controllers.admins.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.entities.Categories;
import org.modelmapper.ModelMapper;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CategoryForm {
	@NotNull
	private Long cateId;
	private boolean use;
	@NotBlank
	private String cateNm;

	@NotNull
	private Long orderNo;
	private String mode;

	public static Categories of (CategoryForm categoryForm) {
		return new ModelMapper().map(categoryForm, Categories.class);
	}

}
