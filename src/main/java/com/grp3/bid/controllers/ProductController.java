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
    public String bid(@RequestParam Integer id, Authentication authentication, @RequestParam("value") Float value) {
        User precUser = offerService.getActualMaxOffer(id).getUser();
        User currentUser = userService.getUserByPseudo(authentication.getName());
        Product currentProduct = productService.getProductByid(id);
        if (!authentication.isAuthenticated()) {
            return "redirect:/product/list";
        }
        if (value > offerService.getActualMaxOffer(currentProduct.getId()).getValue()) {
            if (currentUser.getAccountWallet() >= value) {
                precUser.setAccountWallet(precUser.getAccountWallet() + offerService.getActualMaxOffer(id).getValue());
                userService.updateAccountWallet(precUser);
                currentUser.setAccountWallet(currentUser.getAccountWallet() - value);
                offerService.insertOffer(new Offer(value, LocalDateTime.now(), currentUser, productService.getProductByid(id), currentUser.getUserAddress()));
                userService.updateAccountWallet(currentUser);
            }
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
        List<Category> categories = categoryService.getAll();
        if (authentication.isAuthenticated()) {
            model.addAttribute("product", new Product());
            model.addAttribute("categories", categories);
            return "view-product-form";
        } else {
            // redirection vers la page des produits
            return "redirect:/";
        }
    }

    @PostMapping("product/add")
    public String addProduct(Authentication authentication, @Valid @ModelAttribute("product") Product product, Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-product-form";
        } else {
            if (authentication.isAuthenticated()) {
                System.out.println(product);
                String userName = authentication.getName();
                product.setSeller(userService.getUserByPseudo(userName));
                product.setCategory(category);
                productService.insertProduct(product);
                return "redirect:/";
            } else {
                return "redirect:/";
            }
        }
    }
}


