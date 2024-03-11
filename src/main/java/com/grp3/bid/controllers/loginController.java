package com.grp3.bid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
        @GetMapping("/login")
        String login() {
            return "login";
    }
    @GetMapping("/login-error")
    String loginError() {
        return "login-error";
    }
}
