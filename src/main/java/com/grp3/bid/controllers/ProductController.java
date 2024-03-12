package com.grp3.bid.controllers;

import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@SessionAttributes({ "userLogged" })
public class ProductController {
    @Autowired
    ProductServiceInterface productService;

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
    public String getProductForm(Model model, @ModelAttribute("userLogged")User userLogged) {
        if (userLogged != null && userLogged.getId() >= 1) {
            // Il y a un membre en session
            // Ajout de l'instance dans le modèle
            model.addAttribute("product", new Product());
            return "view-product-add";
        } else {
            // redirection vers la page des produits
            return "redirect:/";
        }
    }

    @PostMapping("product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "view-product-add";
//    } else {
//        // redirection vers la page des films
//        return "redirect:/";
    }
    }

