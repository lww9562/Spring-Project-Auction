package org.koreait.models.bidder;

import org.koreait.controllers.users.UserInfo;
import org.koreait.entities.Bidders;
import org.koreait.entities.Products;
import org.koreait.models.Product.ProductInfoService;
import org.koreait.models.Product.ProductSaveService;
import org.koreait.models.Product.ProductValidationException;
import org.koreait.repositories.BiddersRepository;
import org.koreait.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BidderSaveService {
    @Autowired
    BiddersRepository repository;
    @Autowired
    ProductInfoService infoService;


    public void saveBidder(Long id){
        Products products = infoService.get(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof Bidders) {
            Bidders bidders = (Bidders) principal;

            Long prEndPrice = products.getEndPrice();
            Long prRisingPrice = products.getRisingPrice();
            Long buyCost = prEndPrice+prRisingPrice;

            Long bidderMoney = bidders.getUser().getMoney();
            if( buyCost < bidderMoney ){

                Long bidderModMoney = bidderMoney-buyCost;
                bidders.getUser().setMoney(bidderModMoney);
                repository.saveAndFlush(bidders);
        }

        }
    }

}
