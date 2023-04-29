package org.koreait.controllers.products;

import lombok.RequiredArgsConstructor;
import org.koreait.models.Product.ProductDeleteService;
import org.koreait.models.Product.ProductInfoService;
import org.koreait.models.Product.ProductListService;
import org.koreait.models.Product.ProductSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDeleteService deleteService;
    private final ProductListService listService;
    private final ProductInfoService infoService;
    private final ProductSaveService saveService;

}
