package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Products;
import org.koreait.entities.Users;
import org.koreait.entities.Bidders;
import org.koreait.repositories.BiddersRepository;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {
    private final UsersRepository usersRepository;
    private final BiddersRepository biddersRepository;
    private final ProductRepository productRepository;

    private final BidValidator validator;

    public void bid(String userId, String productId){
        validator.check(userId, productId);

        Users new_users = usersRepository.findByUserId(userId);
        Products products = productRepository.findById(Long.parseLong(productId)).orElse(null);

        Bidders new_bidders = new_users.getBidder();

        List<Products> productsList = new_bidders.getProductList();
        if(productsList.contains(products)){
            productsList.remove(products);
        }
        productsList.add(products);

        new_users.setMoney(new_users.getMoney()-products.getEndPrice());
        usersRepository.saveAndFlush(new_users);

        List<Bidders> biddersList = products.getBidderList();

        /* 기존 최종 입찰자 잔고 환급 S */
        Bidders before_bidder = biddersList.get(biddersList.size()-1);
        Users before_user = before_bidder.getUser();
        before_user.setMoney(before_user.getMoney() + products.getEndPrice());
        usersRepository.saveAndFlush(before_user);
        /* 기존 최종 입찰자 잔고 환급 E */

        biddersList.add(new_bidders);
        products.setBidderList(biddersList);
        products.setEndPrice(products.getEndPrice()+products.getRisingPrice());
        productRepository.saveAndFlush(products);
        biddersRepository.saveAndFlush(new_bidders);
    }
}
