package org.koreait.controllers.products;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Products;
import org.koreait.models.Product.ProductDeleteService;
import org.koreait.models.Product.ProductInfoService;
import org.koreait.models.Product.ProductListService;
import org.koreait.models.Product.ProductSaveService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDeleteService deleteService;
    private final ProductListService listService;
    private final ProductInfoService infoService;
    private final ProductSaveService saveService;
    @GetMapping("/write") //게시글 작성 페이지 이동
    public String write(Model model){
        ProductForm  productForm = new ProductForm();
        model.addAttribute("productForm",productForm);
        return "product/write";
    }
    @PostMapping("/save")
    public String save(@Valid ProductForm productForm, Errors errors){
        try{
            saveService.save(productForm, errors);
        }catch (Exception e){
            errors.reject(e.getMessage());
        }
        //에러 발생시 기존 페이지를 다시 보여주는 코드
        //해당 페이지 아이디가 존재x write.html, 존재O update.html
        //두 페이지 모두 action = @{product/save}
        if (errors.hasErrors()) {
            Long id = productForm.getId();
            if (id == null) {
                return "board/write";
            } else {
                return "board/update";
            }
        }
        return "redirect:/product/list"; //우선 목록 페이지도 있어야 할 것 같아서 목록으로 보냄
    }
    @GetMapping("/update/{id}") //수정 페이지 이동
    public String update(@PathVariable("id") Long id, Model model){
        Products products = infoService.get(id);
        ProductForm productForm = new ModelMapper().map(products,ProductForm.class);//ModelMapper를 사용하여 Products 객체를 ProductForm 객체로 변환하는 코드
        //아마 mode가 ProductForm에 있어서 그런듯.
        model.addAttribute("productForm",productForm);
        return "product/update";
    }
    @GetMapping("/view/{id}") //상세 페이지 이동
    public String view(@PathVariable("id") Long id, Model model) {

        Products products = infoService.get(id);
        model.addAttribute("Product", products);

        return "product/view";
    }
    @GetMapping("/list") //게시글 목록 이동
    public String list(Model model) {

        List<Products> list = listService.gets();

        model.addAttribute("list", list);

        return "product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        deleteService.delete(id);

        return "redirect:/product/list";
    }

}
