package com.grp3.bid.controllers;

import com.grp3.bid.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    OfferServiceInterface offerService;
    @Autowired
    ProductServiceInterface productService;
    @Autowired
    AddressServiceInterface addressService;
    @Autowired
    UserServiceInterface userService;
    @GetMapping("")
    public String adminDashboard(Model model){
        model.addAttribute("offerLst", offerService.getAll());
        model.addAttribute("productLst", productService.getAll());
        model.addAttribute("addressLst", addressService.getAll());
        model.addAttribute("userLst", userService.getAll());
        return "view-admin-dashboard";
    }
}
