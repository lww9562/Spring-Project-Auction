package org.koreait.models.Admin;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Categories;
import org.koreait.entities.Products;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryStatsUpdateService {
	private final CategoryRepository repository;

	public void update(Long categoryId, boolean stats){
		Categories category = repository.findById(categoryId).orElse(null);
		category.setUse(stats);
		repository.saveAndFlush(category);
	}
}
