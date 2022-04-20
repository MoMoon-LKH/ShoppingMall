package com.project.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MemberServiceTest.class)
@Transactional
class MemberServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;


    @Test
    @Rollback
    @DisplayName("가입 테스트")
    public void joinTest() throws Exception {

    }

}