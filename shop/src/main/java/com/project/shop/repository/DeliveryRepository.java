package com.project.shop.repository;

import com.project.shop.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findAllByMember_Id(@Param("memberId") Long memberId);
}
