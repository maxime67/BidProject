package com.grp3.bid.controllers;

import com.grp3.bid.entities.Category;
import com.grp3.bid.services.CategoryServiceInterface;
import com.grp3.bid.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductServiceInterface productService;
    @Autowired
    CategoryServiceInterface categoryService;

    @GetMapping
    public String getAllProducts(Model model){
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("productLst", productService.getAll());
        return "view-product-list";
    }

    @GetMapping("product/list")
    public String getAll(Model model){
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("productLst", productService.getAll());
        return "view-product-list";
    }
    @GetMapping("product/get")
    public String getById(Model model, @RequestParam Integer id){
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("product", productService.getProductByid(id));
        return "view-product-getByid";
    }
    @PostMapping("product/search")
    public String searchProduct(@RequestParam("name") String productName, @RequestParam("categoryId") Long categoryId, Model model){
        System.out.println(productName);
        System.out.println(categoryService.getById(categoryId));
        model.addAttribute("products", productService.getByIdCategory(categoryId.intValue()));
        return "view-product-list";
    }
}
