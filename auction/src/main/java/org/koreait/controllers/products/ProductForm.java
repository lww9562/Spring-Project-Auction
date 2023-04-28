package org.koreait.controllers.products;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.entities.Products;
import org.modelmapper.ModelMapper;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductForm {
    private Long id;
    private String mode;
    @NotBlank
    private String prSubject;
    @NotBlank
    private String prContent;
    @NotBlank
    private Long startPrice;
    @NotBlank
    private Long risingPrice;
    private Long baroPrice;

    public static Products of(ProductForm productForm) {
        return new ModelMapper().map(productForm, Products.class);
    }

}
