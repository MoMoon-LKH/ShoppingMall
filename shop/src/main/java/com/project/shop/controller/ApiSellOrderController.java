package com.project.shop.controller;

import com.project.shop.domain.Orders;
import com.project.shop.domain.enums.OrderStatus;
import com.project.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class ApiSellOrderController {

    private final OrderService orderService;


    @GetMapping("/total")
    public ResponseEntity<?> getTotal() {
        return ResponseEntity.ok(orderService.getTotal());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable
            ) {

        return ResponseEntity.ok(orderService.findAllOrder(pageable));
    }


    @PostMapping("/state/{state}")
    public ResponseEntity<?> changeState(@PathVariable("state") String state, @RequestParam String orderId) {
        Orders order = orderService.findOrderByOrderId(orderId);

        order.orderStatesUpdate(convertOrderStatus(state));

        return ResponseEntity.ok(order);

    }


    public OrderStatus convertOrderStatus(String state) {

        return OrderStatus.DELIVERY_READY;
    }

}
