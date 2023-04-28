package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Products;
import org.koreait.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteService {
    private final ProductRepository repository;
    public void delete(Long id){
        Products products = repository.findById(id).orElseThrow(()->new ProductValidationException("존재하지 않는 게시글입니다."));
        repository.delete(products);
        repository.flush();
    }
}
