package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.products.ProductForm;
import org.koreait.entities.Products;
import org.koreait.entities.Sellers;
import org.koreait.entities.Users;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.SellersRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class ProductSaveService {
    private final ProductRepository repository;
    private final SellersRepository sellersRepository;
    private final UsersRepository usersRepository;
    private final ProductSaveValidator validator;

    public void save(ProductForm productForm){
        save(productForm, null);
    }

    public void save(ProductForm productForm, Errors errors) {
        if (errors != null && errors.hasErrors()) {
            return;
        }
        validator.check(productForm);
//        Products products = productForm.of(productForm);

        //게시글 수정
        Products products = null;

        String mode = productForm.getMode();
        Long id = productForm.getId();
        if (mode != null && mode.equals("update") && id != null) {
            products = repository.findById(id).orElse(null);
            products.setPrSubject(productForm.getPrSubject());
            products.setPrContent(productForm.getPrContent());
        }

        if (products == null) { // 게시글 추가
            products = productForm.of(productForm);
            products.setEndPrice(productForm.getStartPrice());
            products.setSellers(sellersRepository.findByUser(usersRepository.findByUserId(products.getCreatedBy())));
        }

        products = repository.saveAndFlush(products);

        productForm.setId(products.getId());
    }
}
