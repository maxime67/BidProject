package com.grp3.bid.controllers;

import com.grp3.bid.entities.User;
import com.grp3.bid.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/user")
public class UserController {
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
    @GetMapping("/myAccount")
    public String myAccount(Model model, Authentication authentication){
        model.addAttribute("user", userService.getUserByPseudo(authentication.getName()));
        return "view-user-myaccount";
    }
    @GetMapping("/delete")
    public String delete(Authentication authentication){
        userService.deleteUser(userService.getUserByPseudo(authentication.getName()));
        return "view-user-list";
    }
}
