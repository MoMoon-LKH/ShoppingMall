package com.project.shop.service;

import com.project.shop.entity.Member;
import com.project.shop.entity.enums.MemberStatus;
import com.project.shop.exceptions.AlreadyExistIdException;
import com.project.shop.exceptions.NoSuchMemberException;
import com.project.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Long save(Member member) {
        memberRepository.findByMemberId(member.getMemberId())
                .ifPresent(m -> {
                    throw new AlreadyExistIdException();
                });
        return memberRepository.save(member).getId();
    }

    @Transactional
    public boolean delete(Member member) {
        return memberRepository.updateStatusById(MemberStatus.DELETED, member.getId());
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NoSuchMemberException::new);
    }

    public Member findByUserId(String memberId) {
        return memberRepository.findByMemberIdAndStatus(memberId, MemberStatus.ALIVE)
                .orElseThrow(NoSuchMemberException::new);

    }


}