package com.project.shop.controller;


import com.project.shop.domain.Authority;
import com.project.shop.domain.Member;
import com.project.shop.domain.dto.JoinDto;
import com.project.shop.repository.AuthorityRepository;
import com.project.shop.service.AuthorityService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> join(@Valid @RequestBody JoinDto joinDto) {
        joinDto.setPw(passwordEncoder.encode(joinDto.getPw()));
        return ResponseEntity.ok(memberService.save(Member.createMember(joinDto, authorityService.getUserAuthority())));
    }

}
