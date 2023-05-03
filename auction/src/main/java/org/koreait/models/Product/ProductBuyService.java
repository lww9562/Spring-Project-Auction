package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Bidders;
import org.koreait.entities.Products;
import org.koreait.entities.Users;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBuyService {

	private final ProductBuyValidator validator;
	private final UsersRepository usersRepository;
	private final ProductRepository productRepository;
	public void buy(String userId, String productId){
		validator.check(userId, productId);

		//기존의 경매에서 마지막 입찰자에게 돈을 환급해준다.
		Products products = productRepository.findById(Long.parseLong(productId)).orElse(null);

		List<Bidders> bidderList = products.getBidderList();
		//기존 입찰자 잔고 돌려주기
		if(bidderList.size() > 0){
			Bidders before_bidder = bidderList.get(bidderList.size()-1);
			Users before_user = before_bidder.getUser();
			before_user.setMoney(before_user.getMoney() + products.getEndPrice());
			usersRepository.saveAndFlush(before_user);
		}

		//최종 구매자!
		Users buy_user = usersRepository.findByUserId(userId);
		buy_user.setMoney(buy_user.getMoney() - products.getBaroPrice());
		//혹시 몰라 나중을 위해서 우선 경매 리스트 마지막에 추가해둠
		bidderList.add(buy_user.getBidder());
		products.setBidderList(bidderList);

		//경매의 종료! - Products 엔티티 단에서 boolean을 통해 경매 true/false 를 나타낼 수 있도록 추가해야될 듯
		//우선 종료를 알리기 위해, products의 endPrice와 baroPrice를 999999999원으로 변경해두기로 한다.
		products.setEndPrice(999999999L);
		products.setBaroPrice(999999999L);

		usersRepository.saveAndFlush(buy_user);
		productRepository.saveAndFlush(products);
	}
}
