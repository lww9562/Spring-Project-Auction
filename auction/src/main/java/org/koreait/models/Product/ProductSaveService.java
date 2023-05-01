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
//        Products products = productForm.of(productForm);

        Products products = null;

        Categories categories = categoryRepository.findById(productForm.getCategoryId()).orElse(null);
        /*
        System.out.println("============================================");
        System.out.println(categories);
        System.out.println("============================================");
         */

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
            //System.out.println(products.getCreatedBy() + "SELLER!!!!!!!!!!!!!!!!!!!!");
            products.setEndPrice(productForm.getStartPrice());
            products.setCategories(categories);
            //System.out.println(products);

            //System.out.println(usersRepository.findByUserId(products.getCreatedBy()));

            //실제 사용 문장 - 테스트 시 createdBy가 들어가지 않으므로

            Sellers sellers = sellersRepository.findByUser(usersRepository.findByUserId(products.getCreatedBy()));

            /*테스트 문장 - s
            Users users = new Users();
            users.setUserNo(1L);
            users.setUserType(UserType.USER);
            users.setUserId("user01");
            users.setUserPw("12345678");
            users.setUserNm("사용자01");
            users.setMoney(0L);

            Sellers sellers = new Sellers();
            sellers.setUser(users);
            테스트 문장 - e*/
            //사실상 위의 테스트도 억지식이라 별 의미는 없는듯 함

            List<Products> productsList = sellers.getSellProducts();
            productsList.add(products);
            sellers.setSellProducts(productsList);
            products.setSellers(sellers);
            repository.saveAndFlush(products);
        }

//        List<Bidders> bidders = biddersRepository.findByProductOrderByEndPriceDesc(products); // 해당 상품에 대한 입찰 정보를 내림차순으로 가져옴
//        if (!bidders.isEmpty()) {
//            Bidders highestBidder = bidders.get(0); // 가장 높은 입찰가를 제시한 입찰 정보를 가져옴
//            Users users = highestBidder.getUser(); // 해당 입찰 정보를 제시한 구매자 정보를 가져옴
//            products.setBidders(users); // 해당 상품의 구매자 정보를 설정
//            products.setEndPrice(highestBid.getBidPrice()); // 해당 상품의 최종 입찰가를 설정
//        }

        if(input_imgs != null){
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
