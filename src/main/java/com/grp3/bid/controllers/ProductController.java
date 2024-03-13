package com.grp3.bid.controllers;

import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductServiceInterface productService;
    @Autowired
    CategoryServiceInterface categoryService;
    @Autowired
    UserServiceInterface userService;
    @Autowired
    OfferServiceInterface offerService;

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
    @GetMapping("product/bid")
    public String bid(Model model, @RequestParam Integer id, Authentication authentication){
        model.addAttribute("categories", categoryService.getAll());
        if(null == authentication) {
            return "redirect:/product/list";
        }
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.addAttribute("walletAccount", userService.getUserByPseudo(currentUserName).getAccountWallet());
        } else{
            return "redirect:/product/list";
        }
        model.addAttribute("product", productService.getProductByid(id));
        model.addAttribute("actualOffer", offerService.getActualMaxOffer(id));
        return "view-product-bid";
    }
    @PostMapping("product/bid")
    public String bid(@RequestParam Integer id, Authentication authentication, @RequestParam("value") Float value){
        User currentUser = userService.getUserByPseudo(authentication.getName());
        Product currentProduct = productService.getProductByid(id);
        if(!authentication.isAuthenticated()){
            return "redirect:/product/list";
        }
        if(currentUser.getAccountWallet() >= offerService.getActualMaxOffer(id).getValue()){
            currentUser.setAccountWallet(currentUser.getAccountWallet() - offerService.getActualMaxOffer(currentProduct.getId()).getValue());
            offerService.insertOffer(new Offer(value, LocalDateTime.now(), currentUser, productService.getProductByid(id), currentUser.getUser_address()));
        }
        return "redirect:/product/list";
    }
    @PostMapping("product/search")
    public String searchProduct(@RequestParam("name") String productName, @RequestParam("categoryId") Long categoryId, Model model){
        model.addAttribute("categories", categoryService.getAll());
        List<Product> products = productService.getByIdCategory(categoryId.intValue()).stream().filter(p -> p.getName().contains(productName)).toList();
        model.addAttribute("productLst", products);
        System.out.println(productService.getByIdCategory(categoryId.intValue()));
        return "view-product-list";
    }
}
