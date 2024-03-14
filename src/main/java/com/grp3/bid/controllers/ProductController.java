package com.grp3.bid.controllers;

import com.grp3.bid.entities.Category;
import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.CategoryServiceInterface;
import com.grp3.bid.services.OfferServiceInterface;
import com.grp3.bid.services.ProductServiceInterface;
import com.grp3.bid.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    UserService userService;
    @Autowired
    OfferServiceInterface offerService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("productLst", productService.getAll());
        return "view-product-list";
    }

    @GetMapping("product/list")
    public String getAll(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("productLst", productService.getAll());
        return "view-product-list";
    }

    @GetMapping("product/get")
    public String getById(Model model, @RequestParam Integer id) {
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
            offerService.insertOffer(new Offer(value, LocalDateTime.now(), currentUser, productService.getProductByid(id), currentUser.getUserAddress()));
        }
        return "redirect:/product/list";
    }
    @PostMapping("product/search")
    public String searchProduct(@RequestParam("name") String productName, @RequestParam("categoryId") Long categoryId, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        List<Product> products = productService.getByIdCategory(categoryId.intValue()).stream().filter(p -> p.getName().contains(productName)).toList();
        model.addAttribute("productLst", products);
        System.out.println(productService.getByIdCategory(categoryId.intValue()));
        return "view-product-list";
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
        } else {
            if (authentication.isAuthenticated()) {
                System.out.println(product);
                String userName = authentication.getName();
                product.setVendorUser(userService.getUserByPseudo(userName));
                productService.insertProduct(product);
                return "redirect:/";
            } else {
                return "redirect:/";
            }
        }
    }
}


