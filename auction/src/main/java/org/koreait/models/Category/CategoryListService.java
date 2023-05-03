package org.koreait.models.Category;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Categories;
import org.koreait.repositories.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryListService {
    private final CategoryRepository repository;

    public List<Categories> gets(){
        return repository.findAll(Sort.by(Sort.Order.asc("orderNo")));
    }
}
