package org.koreait.models.user;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.mypage.RequestMoneyForm;
import org.koreait.entities.RequestMoney;
import org.koreait.repositories.MoneyRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestMoneyService {


    private final MoneyRepository repository;


    public void saveMoney(RequestMoneyForm requestMoneyForm){



        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = (UserDetails)principal;

        RequestMoney requestMoney = new RequestMoney();
        requestMoney.setMoney(requestMoneyForm.getMoney());
        requestMoney.setUserId(user.getUsername());
        repository.saveAndFlush(requestMoney);


    }


}
