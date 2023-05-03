package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.constants.UserType;
import org.koreait.controllers.products.ProductForm;
import org.koreait.entities.*;
import org.koreait.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSaveService {
    private final ProductRepository repository;
    private final SellersRepository sellersRepository;
    private final UsersRepository usersRepository;
    private final ProductSaveValidator validator;

    private final CategoryRepository categoryRepository;

    public void save(ProductForm productForm) throws Exception{
        save(productForm, null, null);
    }

    public void save(ProductForm productForm, Errors errors) throws Exception{
        save(productForm, errors, null);
    }


    public void save(ProductForm productForm, Errors errors, MultipartFile input_imgs) throws Exception{
        if (errors != null && errors.hasErrors()) {
            return;
        }
        validator.check(productForm);


        Products products = null;
        Categories categories = categoryRepository.findById(productForm.getCategoryId()).orElse(null);

        String mode = productForm.getMode();
        Long id = productForm.getId();
        System.out.println("Before");
        if (mode != null && mode.equals("update") && id != null) {
            products = repository.findById(id).orElse(null);
            products.setPrSubject(productForm.getPrSubject());
            products.setPrContent(productForm.getPrContent());
            products.setCategories(categories);
        }

        if (products == null) { // 게시글 추가
            products = productForm.of(productForm);
            repository.saveAndFlush(products);

            products.setEndPrice(productForm.getStartPrice());
            products.setCategories(categories);


            Sellers sellers = sellersRepository.findByUser(usersRepository.findByUserId(products.getCreatedBy()));



            List<Products> productsList = sellers.getSellProducts();
            productsList.add(products);
            sellers.setSellProducts(productsList);
            products.setSellers(sellers);
            repository.saveAndFlush(products);
        }


        if(!input_imgs.isEmpty()){
            System.out.println("================================================================");
            System.out.println(input_imgs);

            String imgName = input_imgs.getOriginalFilename();
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";

            File saveFile = new File(projectPath, imgName);

            input_imgs.transferTo(saveFile);

            products.setImgName(imgName);
            products.setImgPath("/files/"+imgName);
        }

        products = repository.saveAndFlush(products);

        productForm.setId(products.getId());
    }
}
