package org.koreait.models.Product;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.products.ProductForm;
import org.koreait.entities.Products;
import org.koreait.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductListService {
    private final ProductRepository repository;

    public List<Products> gets(){
        return repository.findAll(Sort.by(Sort.Order.desc("regDt")));
    }
}
