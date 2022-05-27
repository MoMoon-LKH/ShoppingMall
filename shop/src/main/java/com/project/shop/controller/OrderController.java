package com.project.shop.controller;

import com.project.shop.domain.Cart_Item;
import com.project.shop.domain.Delivery;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.CartService;
import com.project.shop.service.DeliveryService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final CartService cartService;
    private final MemberService memberService;
    private final DeliveryService deliveryService;

    @GetMapping("/form")
    public String orderPage(@RequestParam List<Long> item, @AuthenticationPrincipal Account account, Model model) {

        model.addAttribute("items", cartService.findAllCartDtoByCartItemId(item));
        model.addAttribute("member", memberService.findById(account.getId()));
        model.addAttribute("delivery", deliveryService.findAllByMemberId(account.getId()));

        return "/member/order";
    }
}
