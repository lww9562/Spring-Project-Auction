package org.koreait.restcontrollers.product;

import lombok.RequiredArgsConstructor;
import org.koreait.models.Product.BidService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductAjaxController {
    private final BidService bidService;
    @PostMapping("/bid")
    public void bid(String userId, String productId){
        System.out.println("===============================================");
        System.out.println(userId);
        System.out.println(productId);
        bidService.bid(userId, productId);
    }
}
