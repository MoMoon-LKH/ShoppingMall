package com.project.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {


    @GetMapping("/")
    public String indexPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

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
