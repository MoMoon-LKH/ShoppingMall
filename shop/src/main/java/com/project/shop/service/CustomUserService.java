package com.project.shop.service;


import com.project.shop.domain.Member;
import com.project.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        System.out.println("memberId = " + memberId);
        return memberRepository.findByMemberId(memberId)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(memberId + " -> 찾을 수 없는 아이디 입니다."));

    }

    private User createUser(Member member) {
        List<GrantedAuthority> grantedAuthorityList = member.getAuthorities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
        System.out.println("grantedAuthorityList = " + Arrays.toString(grantedAuthorityList.toArray()));
        return new User(member.getMemberId(), member.getPassword(), grantedAuthorityList);
    }
}
