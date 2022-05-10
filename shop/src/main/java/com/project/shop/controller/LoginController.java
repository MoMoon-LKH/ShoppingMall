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
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class LoginController {

    private final LoginService loginService;


    /*@GetMapping("usernick")
    public ResponseEntity<?> getUserNickname(Principal principal) {

    }
*/

}
