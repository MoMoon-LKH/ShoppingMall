package com.project.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/member/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/member/join")
    public String joinPage() {
        return "join";
    }


}
