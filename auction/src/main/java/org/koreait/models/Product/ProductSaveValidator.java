package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.Validator;
import org.koreait.controllers.products.ProductForm;
import org.koreait.repositories.ProductRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSaveValidator implements Validator<ProductForm> {
    private final ProductRepository repository;


    @Override
    public void check(ProductForm productForm) {
        Long id = productForm.getId();
        String mode = productForm.getMode();
        String subject = productForm.getPrSubject();
        String content = productForm.getPrContent();
        Long startPrice = productForm.getStartPrice();
        Long risingPrice = productForm.getRisingPrice();

        requiredCheck(subject, new ProductValidationException("제품명을 입력하세요."));
        requiredCheck(content, new ProductValidationException("내용을 입력하세요."));
        nullCheck(startPrice, new ProductValidationException("시작가격을 입력하세요."));
        nullCheck(risingPrice, new ProductValidationException("인상가격를 입력하세요."));

        if (mode != null && mode.equals("update")) {
            if (id == null) { // 게시글 번호 필수 체크
                nullCheck(id, new ProductValidationException("잘못된 접근입니다."));
            }

            // 게시글 등록 여부 체크
            if (!repository.exists(id)) {
                throw new ProductValidationException("등록되지 않은 게시글 입니다.");
            }
        }
    }

}
