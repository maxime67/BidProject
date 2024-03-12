package com.grp3.bid.controllers;

import com.grp3.bid.DTO.UserWithAddressDTO;
import com.grp3.bid.Mapper.UserWithAddressMapper;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceInterface userService;
    @Autowired
    UserWithAddressMapper userWithAddressMapper;
    @Autowired
    AddressService addressService;

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

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userWithAddressDTO", new UserWithAddressDTO());
        return "view-user-register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("userWithAddressDTO") UserWithAddressDTO userWithAddressDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-user-register";
        }
        User user = userWithAddressMapper.toUser(userWithAddressDTO);
        addressService.insertAddress(user.getUser_address());
        userService.insertUser(user);
        return "view-user-register";
    }
}
