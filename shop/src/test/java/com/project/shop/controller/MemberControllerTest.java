package com.project.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.shop.entity.dto.JoinDto;
import com.project.shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("가입 테스트")
    public void join() throws Exception {

        //given

        JoinDto joinDto = JoinDto.builder()
                .memberId("test")
                .pw("test")
                .nickname("test")
                .gender(0)
                .phone("010-2321-2123")
                .birthday(new SimpleDateFormat("yyyy-MM-dd").parse("1992-09-21"))
                .build();


        //when
        mvc.perform(post("/api/member/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDto)))
                .andExpect(status().isOk())
                .andDo(print());


    }

}