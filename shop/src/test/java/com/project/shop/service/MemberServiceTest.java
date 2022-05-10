package com.project.shop.service;

import com.project.shop.domain.Authority;
import com.project.shop.domain.Member;
import com.project.shop.domain.dto.JoinDto;
import com.project.shop.repository.AuthorityRepository;
import com.project.shop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @Mock
    AuthorityRepository authorityRepository;

    @InjectMocks
    AuthorityService authorityService;

    @Test
    @Rollback
    @Transactional
    @DisplayName("가입 테스트")
    public void joinTest() throws Exception {

        //given
        JoinDto joinDto = JoinDto.builder()
                .memberId("joinTest")
                .pw("joinTest")
                .gender(0)
                .nickname("nickname")
                .birthday(new Date())
                .build();

        //when
        Long saveId = memberService.save(Member.createMember(joinDto,authorityService.getUserAuthority()));


        //then
        assertThat(memberService.findById(saveId).getMemberId()).isEqualTo(joinDto.getMemberId());
    }

}