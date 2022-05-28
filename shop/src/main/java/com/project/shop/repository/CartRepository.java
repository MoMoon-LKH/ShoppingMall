package com.project.shop.repository;

import com.project.shop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByMember_Id(@Param("memberId") Long memberId);


}
