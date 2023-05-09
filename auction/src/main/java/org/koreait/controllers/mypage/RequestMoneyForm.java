package org.koreait.controllers.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.controllers.users.JoinForm;
import org.koreait.entities.RequestMoney;
import org.koreait.entities.Users;
import org.modelmapper.ModelMapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestMoneyForm {


    private String userId;
    private Long money;



    public static RequestMoney of(RequestMoneyForm requestMoneyForm){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return requestMoneyForm == null ? null : modelMapper.map(requestMoneyForm, RequestMoney.class);
    }



}
