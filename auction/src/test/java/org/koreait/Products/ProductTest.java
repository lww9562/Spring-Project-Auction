package org.koreait.Products;

import lombok.extern.java.Log;
import org.aspectj.apache.bcel.Repository;
import org.hibernate.mapping.Join;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controllers.products.ProductForm;
import org.koreait.controllers.users.JoinForm;
import org.koreait.controllers.users.UserInfoService;
import org.koreait.entities.Products;
import org.koreait.entities.Sellers;
import org.koreait.entities.Users;
import org.koreait.models.Product.*;
import org.koreait.models.user.UserJoinService;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Log
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class ProductTest {
    @Autowired
    private ProductSaveService saveService;
    @Autowired
    private ProductListService listService;
    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductDeleteService deleteService;
    @Autowired
    private UserJoinService userJoinService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserInfoService userInfoService;

    private ProductForm productForm;

    private JoinForm joinForm;

    @BeforeEach
    public void init(){
        productForm = ProductForm.builder()
                .id(1L)
                .prSubject("제목")
                .prContent("내용")
                .startPrice(123L)
                .risingPrice(13L)
                .baroPrice(123L)
                .build();

        joinForm = JoinForm.builder()
                .userId("user01")
                .userPw("12345678")
                .userPwRe("12345678")
                .userNm("사용자01")
                .agree(true)
                .build();

    }

    @Test
    @DisplayName("제품 등록 성공시 예외 발생x")
    @WithMockUser("user1")
    void saveSuccessTest(){
        assertDoesNotThrow(()->{
            saveService.save(productForm);
            log.info(productForm.toString());
            saveService.save(productForm);
            log.info(productRepository.findById(1L).toString());
        });

    }
    @Test
    @DisplayName("'제품' 이 null 일시 예외 발생")
    @WithMockUser("user1")
    void nullTest1(){
        assertThrows(ProductValidationException.class,()->{
            productForm.setPrSubject(null);
            saveService.save(productForm);
            log.info(productForm.toString());
        });
    }
    @Test
    @DisplayName("'내용' 이 null 일시 예외 발생")
    @WithMockUser("user1")
    void nullTest2(){
        assertThrows(ProductValidationException.class,()->{
            productForm.setPrContent(null);
            saveService.save(productForm);
        });
    }
    @Test
    @DisplayName("'시작가' 가 null 일시 예외 발생")
    @WithMockUser("user1")
    void nullTest3(){
        assertThrows(ProductValidationException.class,()->{
            productForm.setStartPrice(null);
            saveService.save(productForm);
        });
    }
    @Test
    @DisplayName("'인상가' 가 null 일시 예외 발생")
    @WithMockUser("user1")
    void nullTest4(){
        assertThrows(ProductValidationException.class,()->{
            productForm.setRisingPrice(null);
            saveService.save(productForm);
        });
    }
    @Test
    @DisplayName("mode update 상태, 게시글 수정 성공하면 예외 발생 x")
    @WithMockUser("user1")
    void updateTest(){
        assertDoesNotThrow(()->{
            saveService.save(productForm);
            log.info(productForm.toString());
            productForm.setId(productForm.getId());
            productForm.setMode("update");
            productForm.setPrSubject("(수정)제목");
            log.info(productForm.toString());
            saveService.save(productForm);
            log.info(productForm.toString());
            log.info(productRepository.findById(1L).toString());
        });
    }
    @Test
    @DisplayName("mode update 상태, 제품 id가 다를시 예외 발생")
    @Transactional
    @WithMockUser("user1")
    void updateTest2(){
        assertThrows(ProductValidationException.class,()->{
            saveService.save(productForm);
            log.info(productForm.toString());
            productForm.setId(3L);
            productForm.setMode("update");
            productForm.setPrSubject("(수정)제목");
            saveService.save(productForm);
            log.info(productForm.toString());
        });
    }
    @Test
    @DisplayName("리스트 조회 성공시 예외 발생x")
    @WithMockUser("user1")
    void listTest(){
        assertDoesNotThrow(()->{
            saveService.save(productForm);
            listService.gets();
            log.info(listService.gets().toString());
            List<Products> products = listService.gets();
            log.info(products.toString());
        });
    }
    @Test
    @DisplayName("제품(id) 조회 성공시 예외 발생x")
    @WithMockUser("user1")
    void infoTest(){
        assertDoesNotThrow(()->{
            saveService.save(productForm);
            log.info(productForm.toString());
            Products products = infoService.get(productForm.getId());
            log.info(products.toString());
        });
    }
    @Test
    @DisplayName("삭제 성공시 예외 발생x")
    @WithMockUser("user1")
    void deleteTest(){
        assertDoesNotThrow(()->{
            saveService.save(productForm);
            log.info(productForm.toString());
            deleteService.delete(productForm.getId());
            listService.gets();
            log.info(listService.gets().toString());

            List<Products> products = listService.gets();
            log.info(products.toString());
        });
    }

    @Test
    @DisplayName("게시글 추가시 판매자의 정보도 함께 출력, 게시글의 판매자와 일치하면 예외 발생 x")
    @WithMockUser("user1")
    void sellerJoinTest(){
        assertDoesNotThrow(()->{
            userJoinService.save(joinForm);
            UserDetails userDetails = userInfoService.loadUserByUsername("user01");
            log.info(userDetails.toString());

            saveService.save(productForm);

            Products product = infoService.get(productForm.getId());
            log.info(product.getSellers().getUser().toString());
            if(!userDetails.getUsername().equals(product.getSellers().getUser().getUserId())){
                throw new ProductValidationException("판매자가 일치하지 않습니다.");
            }
            log.info(product.toString());

        }); //기능을 추가해야함. 지금은 안됨
    }
    @Test
    @DisplayName("물품 구매시 게시글의 구매자와 일치하면 예외발생 x")
    void bidderJoinTest(){

    }


}