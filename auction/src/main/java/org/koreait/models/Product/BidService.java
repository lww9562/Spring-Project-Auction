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

    public void bid(String userId, String productId){
        Users users = usersRepository.findByUserId(userId);
        Products products = productRepository.findById(Long.parseLong(productId)).orElse(null);

        Bidders bidders = users.getBidder();

        List<Products> productsList = bidders.getProductList();
        productsList.add(products);

        users.setMoney(users.getMoney()-products.getEndPrice());
        usersRepository.saveAndFlush(users);

        List<Bidders> biddersList = products.getBidderList();
        biddersList.add(bidders);
        products.setBidderList(biddersList);
        products.setEndPrice(products.getEndPrice()+products.getRisingPrice());
        productRepository.saveAndFlush(products);
        biddersRepository.saveAndFlush(bidders);
    }
}
