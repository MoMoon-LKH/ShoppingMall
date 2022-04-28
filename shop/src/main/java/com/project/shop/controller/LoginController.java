package com.project.shop.controller;

import com.project.shop.domain.Member;
import com.project.shop.domain.dto.LoginDto;
import com.project.shop.service.LoginService;
import com.project.shop.service.MemberService;
import com.project.shop.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request) {

        Member loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());

        if (loginMember == null) {
            return ResponseEntity.ok(false);
        }

        // 세션 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return ResponseEntity.ok(loginMember);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }


    @GetMapping("/check")
    public ResponseEntity<?> getSessionMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);


        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (loginMember == null || session == null) {
            return null;
        }


        return ResponseEntity.ok(loginMember.getNickname());
    }


}
