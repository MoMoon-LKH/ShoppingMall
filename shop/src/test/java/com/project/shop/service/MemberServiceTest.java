package com.project.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MemberServiceTest.class)
class MemberServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;


    @Test
    @DisplayName("가입 테스트")
    public void joinTest() throws Exception {

    }

}