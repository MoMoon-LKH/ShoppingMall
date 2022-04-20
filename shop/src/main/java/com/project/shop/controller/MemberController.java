package com.project.shop.controller;


import com.project.shop.entity.Member;
import com.project.shop.entity.dto.JoinDto;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<?> join(@Valid @RequestBody JoinDto joinDto) {
        Long saveId = memberService.save(Member.createMember(joinDto));

        return ResponseEntity.ok(saveId);
    }

}
