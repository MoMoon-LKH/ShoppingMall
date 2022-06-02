package com.project.shop.service;

import com.project.shop.domain.OrderItem;
import com.project.shop.domain.Orders;
import com.project.shop.domain.dto.OrderItemDto;
import com.project.shop.exceptions.NoSuchOrderException;
import com.project.shop.repository.OrderItemRepository;
import com.project.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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


    public Orders findOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId).orElseThrow(NoSuchOrderException::new);
    }

    public List<OrderItemDto> findOrderItemByOrderId(String orderId) {
        return orderItemRepository.findAllByOrder_Id(orderId);
    }
}
