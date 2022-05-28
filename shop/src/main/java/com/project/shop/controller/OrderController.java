package com.project.shop.controller;

import com.project.shop.domain.Delivery;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.CartService;
import com.project.shop.service.DeliveryService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final CartService cartService;
    private final MemberService memberService;
    private final DeliveryService deliveryService;

    @GetMapping("/form")
    public String orderPage(@RequestParam List<Long> item, @AuthenticationPrincipal Account account, Model model) {
        int total = 0;

        List<CartDto> cartDtos = cartService.findAllCartDtoByCartItemId(item);

        model.addAttribute("items", cartDtos);
        model.addAttribute("member", memberService.findById(account.getId()));
        model.addAttribute("delivery", deliveryService.findAllByMemberId(account.getId()));

        for (CartDto cartDto : cartDtos)
            total += cartDto.getCount() * cartDto.getItemCost();

        model.addAttribute("total", total);

        return "/member/order";
    }


    @ResponseBody
    @GetMapping("/member/addresses")
    public ResponseEntity<List<Delivery>> getAddresses(@AuthenticationPrincipal Account account) {
        return ResponseEntity.ok(deliveryService.findAllByMemberId(account.getId()));
    }
}
