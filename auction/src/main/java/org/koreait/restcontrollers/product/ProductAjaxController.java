package org.koreait.restcontrollers.product;

import lombok.RequiredArgsConstructor;
import org.koreait.models.Product.ProductBidService;
import org.koreait.models.Product.ProductBuyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductAjaxController {
    private final ProductBidService bidService;
    private final ProductBuyService buyService;
    @PostMapping("/bid")
    public void bid(String userId, String productId){
        System.out.println("===============================================");
        System.out.println(userId);
        System.out.println(productId);
        bidService.bid(userId, productId);
    }

    @PostMapping("/buy")
    public void buy(String userId, String productId, Model model) {
        model.addAttribute("addScript", new String[]{"buy_button"});
        System.out.println("===============================================");
        System.out.println(userId);
        System.out.println(productId);
        buyService.buy(userId, productId);

    }
}
