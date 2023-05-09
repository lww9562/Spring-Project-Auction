package org.koreait.models.user;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.RequestMoney;
import org.koreait.repositories.MoneyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyListService {
    private final MoneyRepository repository;

    public List<RequestMoney> gets(){
        return repository.findAll(Sort.by(Sort.Order.desc("id")));
    }
}
