package org.koreait.models.Category;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.BadRequestException;
import org.koreait.commons.validators.RequiredValidator;
import org.koreait.commons.validators.Validator;
import org.koreait.controllers.admins.categories.CategoryForm;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategorySaveValidator implements Validator<CategoryForm> {

	private final CategoryRepository repository;


	@Override
	public void check(CategoryForm categoryForm) {
		nullCheck(categoryForm, new BadRequestException("잘못된 접근입니다."));

		//필수항목 검증
		requiredCheck(categoryForm.getCateId().toString(), new BadRequestException("카테고리 ID를 입력하세요."));
		requiredCheck(categoryForm.getCateNm(), new BadRequestException("카테고리 이름을 입력하세요."));
		requiredCheck(categoryForm.getOrderNo().toString(), new BadRequestException("카테고리 순서를 입력하세요."));

		if(repository.exists(categoryForm.getCateId()) || repository.categoryAlreadyExists(categoryForm.getCateNm())){
			throw new DuplicateCategoryException("중복된 카테고리 입니다.");
		}
	}

}
