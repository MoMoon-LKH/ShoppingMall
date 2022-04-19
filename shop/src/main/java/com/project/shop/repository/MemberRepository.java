package com.project.shop.repository;

import com.project.shop.entity.Member;
import com.project.shop.entity.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByMemberIdAndStatus(String userId, MemberStatus memberStatus);

    @Modifying
    @Query("update Member m set m.status = :status where m.id = :id")
    boolean updateStatusById(@Param("status") MemberStatus memberStatus, @Param("id") Long id);


}
