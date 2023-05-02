package org.koreait.restcontrollers.admin;

import org.koreait.entities.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax")
public class Ajax2Controller {
    @GetMapping
    public Users ajaxTest() {

        Users user = Users.builder()
                .userId("user01")
                .userNm("userNm")
                .userPw("123456")
                .email("user01@test.org")
                .build();

        return user;
    }

    @PostMapping("/post")
    public String ajaxTest2(String key1, String key2) {
        System.out.printf("key1=%s, key2=%s%n", key1, key2);

        return "key1=" + key1 + ",key2=" + key2;
    }
}
