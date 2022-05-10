package com.project.shop.controller;


import com.project.shop.domain.Member;
import com.project.shop.domain.dto.JoinDto;
import com.project.shop.service.AuthorityService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public ResponseEntity<?> join(@Valid @RequestBody JoinDto joinDto) {
        joinDto.setPw(passwordEncoder.encode(joinDto.getPw()));
        return ResponseEntity.ok(memberService.save(Member.createMember(joinDto, authorityService.getUserAuthority())));
    }

    @GetMapping("/usernickname")
    public ResponseEntity<?> currentMember(Principal principal) {
        try {
            Member member = memberService.findByMemberId(principal.getName());
            Map<String, Object> map = new HashMap<>();
            map.put("id", member.getId());
            map.put("nickname", member.getNickname());

            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

}
