package com.example.demo.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acc")
public class AccountController {
    @GetMapping
    public String login() {
        return "account/login";
    }


    @GetMapping("/register")
    public String register() {
        return "account/register";
    }
}
