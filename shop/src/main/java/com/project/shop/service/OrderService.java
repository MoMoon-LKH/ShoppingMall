package com.project.shop.service;

import com.project.shop.domain.Orders;
import com.project.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Long save(Orders order) {
        return orderRepository.save(order).getId();
    }

}
