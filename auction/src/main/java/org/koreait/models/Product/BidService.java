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

        //물품을 통해 물품의 기존 구매자 내역을 받아옴
        List<Bidders> biddersList = products.getBidderList();

        /* 기존 최종 입찰자 잔고 환급 S */
        if(biddersList.size() > 0){
            Bidders before_bidder = biddersList.get(biddersList.size()-1);
            Users before_user = before_bidder.getUser();
            before_user.setMoney(before_user.getMoney() + products.getEndPrice() - products.getRisingPrice());
            usersRepository.saveAndFlush(before_user);
        }
        /* 기존 최종 입찰자 잔고 환급 E */

        //새로운 구매자가 이전에 해당 물품을 구매했던 내역이 존재하면
        //해당 내역은 삭제한다.
        List<Products> productsList = new_bidders.getProductList();
        if(productsList.contains(products)){
            productsList.remove(products);
        }
        //그 후, 내역에 새로운 물품에 대한 부분을 추가한다.
        productsList.add(products);

        //새로운 구매자의 잔고 반영
        new_users.setMoney(new_users.getMoney()-products.getEndPrice());
        usersRepository.saveAndFlush(new_users);



        biddersList.add(new_bidders);
        products.setBidderList(biddersList);
        products.setEndPrice(products.getEndPrice()+products.getRisingPrice());
        productRepository.saveAndFlush(products);
        biddersRepository.saveAndFlush(new_bidders);
    }
}
