package com.project.shop.repository;

import com.project.shop.domain.OrderItem;
import com.project.shop.domain.dto.ItemNameDto;
import com.project.shop.domain.dto.OrderItemDto;
import com.project.shop.domain.dto.OrderListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    @Query("select new com.project.shop.domain.dto.OrderItemDto(" +
            "oi.id, oi.item.id, oi.item.name, oi.item.imgUrl, oi.cost, oi.count" +
            ") from OrderItem oi where oi.order.orderId = :orderId")
    List<OrderItemDto> findAllByOrder_Id(@Param("orderId") String orderId);


    @Query("select " +
            "min(oi.item.name), oi.item.imgUrl from OrderItem oi " +
            "where oi.order.id = :orderId")
    String findItemNameAndImgUrlByOrderId(@Param("orderId") Long orderId);

    @Query("select new com.project.shop.domain.dto.ItemNameDto( " +
            "min(oi.item.name), oi.item.imgUrl)from OrderItem oi " +
            "where oi.order.id = :orderId")
    ItemNameDto findItemNameDtoByOrderId(@Param("orderId") Long orderId);
}
