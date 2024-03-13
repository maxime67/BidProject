package com.grp3.bid.controllers;

import com.grp3.bid.entities.Product;
import com.grp3.bid.services.ProductServiceInterface;
import com.grp3.bid.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductServiceInterface productService;

    @Autowired
    UserService userService;

    @GetMapping
    public String getAllProducts(Model model){
        model.addAttribute("productLst", productService.getAll());
        return "view-product-list";
    }

    @GetMapping("product/list")
    public String getAll(Model model){
        model.addAttribute("productLst", productService.getAll());
        return "view-product-list";
    }
    @GetMapping("product/get")
    public String getById(Model model, @RequestParam Integer id){
        model.addAttribute("product", productService.getProductByid(id));
        return "view-product-getByid";
    }

    @GetMapping("product/add")
    public String getProductForm(Model model, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            model.addAttribute("product", new Product());
            return "view-product-form";
        } else {
            // redirection vers la page des produits
            return "redirect:/";
        }
    }

    @PostMapping("product/add")
    public String addProduct(Authentication authentication, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-product-form";
        }else{
            if (authentication.isAuthenticated()) {
                System.out.println(product);
                String userName = authentication.getName();
                product.setSeller(userService.getUserByPseudo(userName));
                productService.insertProduct(product);
                return "redirect:/";
            } else {
                return "redirect:/";
            }
        }
    }
    }

