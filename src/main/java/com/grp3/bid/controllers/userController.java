package com.grp3.bid.controllers;

import com.grp3.bid.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    UserServiceInterface userService;
    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("userLst", userService.getAll());
        return "view-user-list";
    }
    @GetMapping("/get")
    public String getById(Model model, @RequestParam Integer id){
        model.addAttribute("user", userService.getUserByid(id));
        return "view-user-getByid";
    }
}
