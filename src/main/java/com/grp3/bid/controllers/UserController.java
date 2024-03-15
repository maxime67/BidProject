package com.grp3.bid.controllers;

import com.grp3.bid.DTO.EditUserDTO;
import com.grp3.bid.DTO.UserWithAddressDTO;
import com.grp3.bid.Mapper.EditUserMapper;
import com.grp3.bid.Mapper.UserWithAddressMapper;
import com.grp3.bid.Utils.AuthenticationFacade;
import com.grp3.bid.Utils.AuthenticationFacadeInterface;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    AuthenticationFacadeInterface authenticationFacade;
    @Autowired
    AddressService addressService;
    @Autowired
    ProductServiceInterface productService;
    @Autowired
    UserWithAddressMapper userWithAddressMapper;
    @Autowired
    EditUserMapper editUserMapper;

    @GetMapping("/list")
    public String getAll(Model model){
        model.addAttribute("userLst", userService.getAll());
        return "view-user-list";
    }
    @GetMapping("/get")
    public String getById(Model model, @RequestParam Integer id){
        model.addAttribute("user", userService.getUserByid(id));
        model.addAttribute("productLst", productService.getProductListByIdSeller(userService.getUserByid(id)));
        return "view-user-getByid";
    }
    @GetMapping("/myAccount")
    public String myAccount(Model model, Authentication authentication){
        if(null != authentication) {
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                model.addAttribute("user", userService.getUserByPseudo(authentication.getName()));
                return "view-user-getByid";
            }
            return "redirect:/product/list";
        }
        return "redirect:/product/list";

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
        userService.insertUser(user);
        return "redirect:/product/list";
    }

    @GetMapping("/editAccount")
    public String editAccount(Model model){
        model.addAttribute("userWithAddressDTO", editUserMapper.toDTO(authenticationFacade.getConnectedUser()));
        return "view-user-editAccount";
    }

    @PostMapping("/editAccount")
    public String editAccountPost(@Valid @ModelAttribute("userWithAddressDTO") EditUserDTO editUserDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "view-user-editAccount";
        }
        User user = editUserMapper.toUser(editUserDTO);
        userService.updateUser(user);
        return "redirect:/logout";
    }
}
