package com.project.shop.service;

import com.project.shop.domain.Authority;
import com.project.shop.domain.Cart;
import com.project.shop.domain.Member;
import com.project.shop.domain.dto.JoinDto;
import com.project.shop.domain.enums.Gender;
import com.project.shop.repository.CartRepository;
import com.project.shop.repository.Cart_ItemRepository;
import com.project.shop.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private Cart_ItemRepository cart_itemRepository;
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private CartService cartService;

    @InjectMocks
    private MemberService memberService;


    @Test
    @DisplayName("장바구니 생성")
    void cart_save() {
        //given
        Member findMember = memberService.findByMemberId("test");
        Cart cart = Cart.createCart(findMember);


        //when
        Long save = cartService.save(cart);


        //then

    }


    @BeforeEach
    void beforeEach() {
        JoinDto join = JoinDto.builder()
                .memberId("test")
                .nickname("test")
                .pw("test")
                .gender(1)
                .birthday(new Date("1995-02-12"))
                .phone("022-2123-2321")
                .build();

        Authority authority = new Authority();

        Member member = Member.createMember(join, authority);
        memberService.save(member);
    }


}