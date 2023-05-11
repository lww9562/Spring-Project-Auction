package org.koreait.controllers.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainRedirectController {

    //그냥 주소만 입력시 메인으로 리다이렉트 해줌.
    //localhost:3000 -> localhost:3000/main
    @RequestMapping("/")
    public String mainRedirect(){
        return "redirect:/main";
    }

}
