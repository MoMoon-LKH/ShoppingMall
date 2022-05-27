package com.project.shop.controller;


import com.project.shop.domain.Cart;
import com.project.shop.domain.Delivery;
import com.project.shop.domain.Member;
import com.project.shop.domain.dto.JoinDto;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.AuthorityService;
import com.project.shop.service.CartService;
import com.project.shop.service.DeliveryService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final DeliveryService deliveryService;


    @PostMapping("/signup")
    public ResponseEntity<?> join(@Valid @RequestBody JoinDto joinDto) {
        joinDto.setPw(passwordEncoder.encode(joinDto.getPw()));
        Long saveId = memberService.save(Member.createMember(joinDto, authorityService.getUserAuthority()));
        Member member = memberService.findById(saveId);

        Cart cart = Cart.createCart(member);
        Long cartId = cartService.save(cart);

        Delivery delivery = Delivery.createDelivery(joinDto.getZipcode(), joinDto.getAddress(), joinDto.getAddrDetail(), member);
        Long delId = deliveryService.save(delivery);

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", saveId);
        map.put("cartId", cartId);
        map.put("deliveryId", delId);

        return ResponseEntity.ok(map);
    }
    

    @GetMapping("/usernickname")
    public ResponseEntity<?> currentMember(@AuthenticationPrincipal Account account) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", account.getId());
            map.put("nickname", account.getNickname());

            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }

    }

}
