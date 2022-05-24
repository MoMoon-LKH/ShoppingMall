package com.project.shop.controller;

import com.project.shop.domain.userDetails.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/member/cart")
    public String cartPage(@AuthenticationPrincipal Account account, Model model) {
        model.addAttribute("memberId", account.getId());
        return "/member/cart";
    }

}
