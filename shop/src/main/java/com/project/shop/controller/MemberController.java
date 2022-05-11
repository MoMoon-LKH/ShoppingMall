package com.project.shop.controller;

import com.project.shop.domain.userDetails.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {


    @GetMapping("/")
    public String indexPage(@AuthenticationPrincipal Account account, Model model) {
        try {
            model.addAttribute("id", account.getId());
            model.addAttribute("nickname", account.getNickname());
        } catch (Exception ignored) {

        }
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
