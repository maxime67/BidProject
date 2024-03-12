package com.grp3.bid.controllers;

import com.grp3.bid.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceInterface productService;
    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("productLst", productService.getAll());
        return "view-product-list";
    }

    @GetMapping("/get")
    public String getById(Model model, @RequestParam Integer id){
        model.addAttribute("product", productService.getProductByid(id));
        return "view-product-getByid";
    }
}
