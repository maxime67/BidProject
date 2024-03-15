package com.grp3.bid.controllers;

import com.grp3.bid.entities.*;
import com.grp3.bid.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    OfferServiceInterface offerService;
    @Autowired
    ProductServiceInterface productService;
    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("offerLst", offerService.getAll());
        return "view-offer-list";
    }
    @GetMapping("/get")
    public String getById(Model model, @RequestParam Integer id) {
        model.addAttribute("offer", offerService.getOfferByid(id));
        return "view-offer-getByid";
    }
    @GetMapping("/create")
    public String create(Model model, @RequestParam Integer id){
        model.addAttribute("product",productService.getProductByid(id));
        model.addAttribute("offer",new Offer());
        return "view-offer-create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("offer") Offer offer, Product product, User user){
        offer.setProduct(product);
        offer.setUser(user);
        return "redirect:/offer/list";
    }
}
