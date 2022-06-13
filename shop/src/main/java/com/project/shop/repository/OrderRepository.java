package com.project.shop.repository;

import com.project.shop.domain.Orders;
import com.project.shop.domain.dto.OrderItemDto;
import com.project.shop.domain.dto.OrderListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByOrderId(@Param("orderId") String orderId);

    List<Orders> findAllByMember_Id(@Param("memberId") Long memberId, Pageable pageable);


    @Query("select new com.project.shop.domain.dto.OrderListDto(o.id, o.orderId, o.orderId, o.orderId, o.order_items.size, o.cost, o.createDate, o.status) from Orders o " +
            "where o.member.id = :memberId")
    List<OrderListDto> findAllByOrdersList(@Param("memberId") Long memberId, Pageable pageable);


    @Query("select " +
            "new com.project.shop.domain.dto.OrderListDto( " +
            "o.id, o.orderId, o.orderId, o.orderId, o.order_items.size, o.cost, o.createDate, o.status " +
            ") " +
            "from Orders o")
    List<OrderListDto> findAllOrder( Pageable pageable);
}
