package org.koreait.Products;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controllers.products.ProductForm;
import org.koreait.entities.Products;
import org.koreait.models.Product.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

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
    private ProductForm productForm;


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

    }

    @Test
    @DisplayName("제품 등록 성공시 예외 발생x")
    @WithMockUser("user1")
    void saveSuccessTest(){
        assertDoesNotThrow(()->{
            saveService.save(productForm);
            log.info(productForm.toString());
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
            saveService.save(productForm);
            log.info(productForm.toString());
        });
    }
    @Test
    @DisplayName("mode update 상태, 제품 id가 다를시 예외 발생")
    @WithMockUser("user1")
    void updateTest2(){
        assertThrows(ProductValidationException.class,()->{

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
        });
    }

}
