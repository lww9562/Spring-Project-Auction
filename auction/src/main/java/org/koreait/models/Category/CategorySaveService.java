package org.koreait.models.Category;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.categories.CategoryForm;
import org.koreait.entities.Categories;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class CategorySaveService {
	private final CategoryRepository repository;

	public void save(CategoryForm categoryForm) {
		save(categoryForm, null);
	}

	public void save(CategoryForm categoryForm, Errors errors){
		if(errors != null && errors.hasErrors()){
			return;
		}

		//validator 검증부분

		//cateId를 통해 기존에 생성된 카테고리인지 확인한 후,
		//기존에 생성된 카테고리인 경우 가져와 값을 변경하고,
		//그렇지 않으면 새로운 카테고리 엔티티 생성

		Long cateId = categoryForm.getCateId();
		Categories category = null;
		if(cateId != null && repository.existsById(cateId)){
			category = repository.findById(cateId).orElse(null);
			category.setCateId(cateId);
			category.setCateNm(categoryForm.getCateNm());
			category.setOrderNo(categoryForm.getOrderNo());
		}

		if(category == null){
			category = CategoryForm.of(categoryForm);
		}

//		String mode = categoryForm.getMode();
//		Long id = categoryForm.getCateId();
//		if (mode != null && mode.equals("update") && id != null) {
//			category = repository.findById(cateId).orElse(null);
//			category.setCateId(cateId);
//			category.setCateNm(categoryForm.getCateNm());
//			category.setOrderNo(categoryForm.getOrderNo());
//		}

		repository.saveAndFlush(category);
	}
}
