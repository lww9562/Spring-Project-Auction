package org.koreait.controllers.main;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Products;
import org.koreait.models.Product.ProductListService;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {
    private final ProductListService listService;
    private final CategoryRepository categoryRepository;
    @GetMapping
    public String main(Model model){


        List<Products> list = listService.gets();
        List<String> cateNmList = categoryRepository.getAllCateNm();

        model.addAttribute("list", list);
        model.addAttribute("cateNmList", cateNmList);


        return "/main";
    }
}
