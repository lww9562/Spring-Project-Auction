package org.koreait.controllers.products;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.Pagination;
import org.koreait.controllers.users.UserInfo;
import org.koreait.entities.Categories;
import org.koreait.entities.Products;
import org.koreait.models.Category.CategorySaveService;
import org.koreait.models.Product.ProductDeleteService;
import org.koreait.models.Product.ProductInfoService;
import org.koreait.models.Product.ProductListService;
import org.koreait.models.Product.ProductSaveService;
import org.koreait.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDeleteService deleteService;
    private final ProductListService listService;
    private final ProductInfoService infoService;
    private final ProductSaveService saveService;
    private final CategoryRepository categoryRepository;
    private final CategorySaveService categorySaveService;


    @GetMapping("/write") //게시글 작성 페이지 이동
    public String write(Model model){
        ProductForm productForm = new ProductForm();
        model.addAttribute("productForm",productForm);

        List<Categories> categories = categoryRepository.findAll();

        List<Categories> listCategories = new ArrayList<>();

        for (Categories category : categories) {
            if(category.isUse()){
                listCategories.add(category);
            }
        }
        Collections.sort(listCategories);

        model.addAttribute("categoryMap", listCategories.stream().collect(Collectors.toMap(Categories::getCateId, Categories::getCateNm)));


        return "product/write";
    }

    @PostMapping("/save")
    public String save(@Valid ProductForm productForm, Errors errors , MultipartFile input_imgs){
        try{
            saveService.save(productForm, errors, input_imgs);
        }catch (Exception e){
            errors.reject(e.getMessage());
        }
        //에러 발생시 기존 페이지를 다시 보여주는 코드
        //해당 페이지 아이디가 존재x write.html, 존재O update.html
        //두 페이지 모두 action = @{product/save}
        if (errors.hasErrors()) {
            Long id = productForm.getId();
            if (id == null) {
                return "product/write";     // board/write로 쓴 부분 수정
            } else {
                return "product/update";    // board/write로 쓴 부분 수정
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
        model.addAttribute("addScript", new String[]{"bid_button", "buy_button"});

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String userId = userDetails.getUsername();

        model.addAttribute("userId", userId);

        Products products = infoService.get(id);
        model.addAttribute("product", products);

        return "product/view";
    }
    /**
    @GetMapping("/list") //게시글 목록 이동
    public String list(Model model) {

        List<Products> list = listService.gets();
        List<Products> list1 = listService.getsOrderByBaroPrice();
        List<Products> list2 = listService.getsOrderByEndPrice();
        List<Products> list3 = listService.getsOrderByLastTime();
        List<Products> list4 = listService.getsOrderByNewPr();

        List<String> cateNmList = categoryRepository.getAllCateNm();

        model.addAttribute("list", list);
        model.addAttribute("listBaro", list1);
        model.addAttribute("listEndPrice", list2);
        model.addAttribute("listLastTime", list3);
        model.addAttribute("listNewPr", list4);

        model.addAttribute("cateNmList", cateNmList);

        return "product/list";
    }
    */

    @GetMapping("/list")
    public String list(ProductSearch search, Model model, HttpServletRequest request) throws Exception {
        System.out.println(search);

        Page<Products> products = listService.gets(search);

        String url = request.getContextPath() + "/product/list";
        System.out.println(url);




        System.out.println("=================default URL=================");
        String qs = Arrays.stream(request.getQueryString().split("&")).filter(s -> !s.contains("page")).collect(Collectors.joining("&"));

        url += "?" + qs;
        System.out.println("===========================================");
        System.out.println(qs);
        System.out.println(url);

        url = URLDecoder.decode(url, "UTF-8");

        Pagination<Products> pagination = new Pagination<>(products, url);
        model.addAttribute("products", products.getContent());
        model.addAttribute("pagination", pagination);
        List<String> cateNmList = categoryRepository.getAllCateNm();
        model.addAttribute("cateNmList", cateNmList);
        return "product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        deleteService.delete(id);

        return "redirect:/product/list";
    }

}
