package com.project.shop.service;

import com.project.shop.domain.OrderItem;
import com.project.shop.domain.Orders;
import com.project.shop.domain.dto.*;
import com.project.shop.domain.enums.OrderStatus;
import com.project.shop.exceptions.NoSuchOrderException;
import com.project.shop.repository.OrderItemRepository;
import com.project.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    @Transactional
    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Transactional
    public int saveOrderItems(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems).size();
    }

    @Transactional
    public void updateOrderState(Orders orders, OrderStatus orderStatus) {
        orders.orderStatesUpdate(orderStatus);

    }

    @Transactional
    public void updateOrderDelivery(Orders orders, DeliveryDto deliveryDto) {
        orders.deliveryUpdate(deliveryDto);

    }

    public Orders findOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId).orElseThrow(NoSuchOrderException::new);
    }

    public List<Orders> findAllByMemberId(Long memberId, Pageable pageable) {
        return orderRepository.findAllByMember_Id(memberId, pageable);
    }


    public List<OrderItemDto> findOrderItemByOrderId(String orderId) {
        return orderItemRepository.findAllByOrder_Id(orderId);
    }

    public List<OrderListDto> findOrderListByMemberId(Long memberId, Pageable pageable) {
        return orderRepository.findAllByOrdersList(memberId, pageable);
    }

    public List<OrderListDto> findAllOrder(Pageable pageable) {
        return orderRepository.findAllOrder(pageable);
    }


    public ItemNameDto findItemNameByOrder_Id(Long orderId) {
       /* Map<String, String> map = new HashMap<>();
        String str = orderItemRepository.findItemNameAndImgUrlByOrderId(orderId);
        String[] strings = str.split(",");
        map.put("name", strings[0]);
        map.put("img", strings[1]);*/

        return orderItemRepository.findItemNameDtoByOrderId(orderId);
    }

    public List<OrderListDto> findAllOrderBySearch(OrderSearchDto orderSearchDto, Pageable pageable) {
        return orderRepository.findAllOrderBySearch(orderSearchDto, pageable);
    }

    public Long countOrderBySearch(OrderSearchDto orderSearchDto) {
        return orderRepository.countOrderBySearch(orderSearchDto);
    }

    public Long getTotal() {
        return orderRepository.count();
    }

}
