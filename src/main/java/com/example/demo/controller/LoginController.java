package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.LoginService;


@Controller
public class LoginController {

    @Autowired
    private LoginService serv;

    @GetMapping("/")
    public String getInitialPage() {
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (serv.log(username, password)) {
            return "home";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        if(serv.signUp(username, password)) {
            return "home"; 
        }
        return "signup"; 
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home"; 
    }
}
