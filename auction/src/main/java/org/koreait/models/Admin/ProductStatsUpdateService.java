package org.koreait.models.Admin;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Products;
import org.koreait.entities.Users;
import org.koreait.repositories.ProductRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStatsUpdateService {
	private final ProductRepository repository;

	public void update(Long productId, boolean stats){
		Products product = repository.findById(productId).orElse(null);
		product.setStats(stats);
		repository.saveAndFlush(product);
	}
}
