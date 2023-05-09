package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.BadRequestException;
import org.koreait.commons.validators.RequiredValidator;
import org.koreait.entities.Bidders;
import org.koreait.entities.Products;
import org.koreait.entities.Sellers;
import org.koreait.entities.Users;
import org.koreait.repositories.BiddersRepository;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductBidValidator implements RequiredValidator {
	private final UsersRepository usersRepository;
	private final ProductRepository productRepository;
	private final BiddersRepository biddersRepository;


	public void check(String userId, String productId) {
		requiredCheck(userId, new BadRequestException("사용자를 찾을 수 없습니다."));
		requiredCheck(productId, new BadRequestException("판매중인 물품을 찾을 수 없습니다."));


		Users user = usersRepository.findByUserId(userId);
		Products product = productRepository.findById(Long.parseLong(productId)).orElse(null);
		nullCheck(user, new BadRequestException("사용자를 찾을 수 없습니다."));
		nullCheck(product, new BadRequestException("판매중인 물품을 찾을 수 없습니다."));

		
		// 1. 입찰자의 소지금이 판매물픔의 현재 입찰가보다 적은 경우, 오류 발생
		if(user.getMoney() < product.getEndPrice()){
			//구매자의 소지금이 판매물품의 금액보다 적은 경우
			throw new BadRequestException("잔고가 부족합니다!");
		}

		
		
		// 2. 판매자가 자신의 물품에 대해 입찰을 진행할 경우, 오류 발생
		Sellers seller = product.getSellers();
		if(user.equals(seller.getUser())){
			//판매자가 자신의 물품에 입찰 버튼을 누른 상황이다.
			throw new BadRequestException("자신의 판매글에는 입찰하실 수 없습니다!");
		}
		

		
		// 3. 같은 물품에 대해 연속적으로 입찰할 경우, 오류 발생
		List<Bidders> biddersList = product.getBidderList();

		if(biddersList.size() > 0){
			if(user.equals(biddersList.get(biddersList.size()-1).getUser())){
				System.out.println(biddersList.get(biddersList.size()-1).getUser());
				//bidder가 자신이 마지막으로 입찰한 물건에 재입찰할 시 오류 발생
				throw new BadRequestException("자신이 마지막으로 입찰한 물건에는 입찰하실 수 없습니다!");
			}
		}

	}

}
