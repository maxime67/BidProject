package com.grp3.bid.controllers;

import com.grp3.bid.DTO.EditUserDTO;
import com.grp3.bid.DTO.ForgottenPasswordDTO;
import com.grp3.bid.DTO.UserWithAddressDTO;
import com.grp3.bid.Mapper.EditUserMapper;
import com.grp3.bid.Mapper.UserWithAddressMapper;
import com.grp3.bid.Utils.AuthenticationFacade;
import com.grp3.bid.Utils.AuthenticationFacadeInterface;
import com.grp3.bid.entities.ResetPasswdToken;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    @Autowired
    ResetPasswdTokenServiceInterface resetPasswdTokenService;
    @Autowired
    DeleteUserInterface deleteUser;

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
                return "view-user-myaccount";
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
    @PostMapping("/deleteCurrent")
    public String deleteCurrentAccount(){
        deleteUser.deleteUser(authenticationFacade.getConnectedUser());
        return "redirect:/login";
    }
    @PostMapping("/deleteTarget")
    public String deleteTargetAccount(@RequestParam("id") Integer id){
        deleteUser.deleteUser(userService.getUserByid(id));
        return "redirect:/admin";
    }
    @GetMapping("/forgottenPassword")
    public String forgottenPassword(Model model) {
        model.addAttribute("step", "request");
        return "view-user-forgottenPassword";
    }

    @PostMapping("/forgottenPassword")
    public String forgottenPasswordPost(@RequestParam String email, Model model) {
        User user = null;
        try {
            user = userService.getUserByemail(email);
        } catch (EmptyResultDataAccessException ignored) {}
        if (null != user) {
            resetPasswdTokenService.createRequestResetPasswdTokenForUser(user);
        }
        model.addAttribute("step", "accepted");
        return "view-user-forgottenPassword";
    }

    @GetMapping("/forgottenPassword/{token}")
    public String forgottenPasswordReset(@PathVariable String token, Model model) {
        ResetPasswdToken resetPasswdToken = resetPasswdTokenService.getResetPasswdTokenByToken(token);
        if (null == resetPasswdToken) {
            model.addAttribute("step", "expired");
        } else {
            model.addAttribute("step", "resetPasswordForm");
            model.addAttribute("forgottenPasswordDTO", new ForgottenPasswordDTO(token, null));
        }
        return "view-user-forgottenPassword";
    }

    @PostMapping("/forgottenPassword/reset")
    public String forgottenPasswordResetPost(@RequestParam String token, @Valid @ModelAttribute("forgottenPasswordDTO") ForgottenPasswordDTO forgottenPasswordDTO, BindingResult bindingResult, Model model) {
        ResetPasswdToken resetPasswdToken = resetPasswdTokenService.getResetPasswdTokenByToken(token);
        if (null == resetPasswdToken) {
            model.addAttribute("step", "expired");
            return "view-user-forgottenPassword";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("step", "resetPasswordForm");
            return "view-user-forgottenPassword";
        }

        userService.updateUserPassword(resetPasswdToken.getUser(), forgottenPasswordDTO.getPassword());
        resetPasswdTokenService.updateAlreadyUsed(resetPasswdToken, true);
        model.addAttribute("step", "done");
        return "view-user-forgottenPassword";
    }
}
