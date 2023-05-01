package org.koreait.controllers.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.entities.Categories;
import org.koreait.entities.Products;
import org.modelmapper.ModelMapper;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductForm {
    private String writer;  //작성자 추가
    private Long id;
    private String mode;
    @NotBlank
    private String prSubject;
    @NotBlank
    private String prContent;
    @NotNull
    private Long startPrice;
    @NotNull
    private Long risingPrice;
    private Long baroPrice;
    @NotNull
    private Long categoryId;


    public static Products of(ProductForm productForm) {
        Products products= new ModelMapper().map(productForm, Products.class);
        System.out.println("============createdby==============");
        System.out.println(products.getCreatedBy());
        return products;
    }

}
