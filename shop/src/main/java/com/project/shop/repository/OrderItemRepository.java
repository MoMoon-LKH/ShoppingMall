package com.project.shop.repository;

import com.project.shop.domain.OrderItem;
import com.project.shop.domain.dto.OrderItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    @Query("select new com.project.shop.domain.dto.OrderItemDto(" +
            "oi.id, oi.item.id, oi.item.name, oi.item.imgUrl, oi.item.cost, oi.count" +
            ") from OrderItem oi where oi.order.orderId = :orderId")
    List<OrderItemDto> findAllByOrder_Id(@Param("orderId") String orderId);
}
