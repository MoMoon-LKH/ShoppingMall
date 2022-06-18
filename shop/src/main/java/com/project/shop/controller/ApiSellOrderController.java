package com.project.shop.controller;

import com.project.shop.domain.Orders;
import com.project.shop.domain.dto.DeliveryDto;
import com.project.shop.domain.dto.ItemNameDto;
import com.project.shop.domain.dto.OrderListDto;
import com.project.shop.domain.dto.OrderSearchDto;
import com.project.shop.domain.enums.OrderStatus;
import com.project.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ) throws ParseException {
        List<OrderListDto> allOrder = orderService.findAllOrder(pageable);

        for (OrderListDto order : allOrder) {
            ItemNameDto itemName = orderService.findItemNameByOrder_Id(order.getId());
            order.setItemName(itemName.getName());
            order.setImgUrl(itemName.getImg());

        }

        return ResponseEntity.ok(allOrder);
    }


    @PostMapping("/list/search")
    public ResponseEntity<?> getListSearch(
            @Valid @RequestBody(required = false) OrderSearchDto orderSearchDto,
            @PageableDefault Pageable pageable
    ) {


        Long total = orderService.countOrderBySearch(orderSearchDto);

        List<OrderListDto> orderList = orderService.findAllOrderBySearch(orderSearchDto, pageable);

        for (OrderListDto order : orderList) {
            ItemNameDto itemName = orderService.findItemNameByOrder_Id(order.getId());
            order.setItemName(itemName.getName());
            order.setImgUrl(itemName.getImg());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", orderList);

        return ResponseEntity.ok(map);
    }


    @PostMapping("/state")
    public ResponseEntity<?> changeState(@RequestParam String state, @RequestParam String orderId) {

        Orders order = orderService.findOrderByOrderId(orderId);

        orderService.updateOrderState(order, convertOrderStatus(state));

        return ResponseEntity.ok(order.getStatus());

    }

    @PostMapping("/delivery/update")
    public ResponseEntity<?> changeDelivery(
            @Valid @RequestBody DeliveryDto deliveryDto
            ) {

        Orders order = orderService.findOrderByOrderId(deliveryDto.getOrderId());

        orderService.updateOrderDelivery(order, deliveryDto);

        return ResponseEntity.ok(convertDeliveryDto(order));
    }


    public OrderStatus convertOrderStatus(String state) {

        switch (state) {
            case "ITEM_READY":
                return OrderStatus.ITEM_READY;
            case "DELIVERY_READY":
                return OrderStatus.DELIVERY_READY;
            case "DELIVERY":
                return OrderStatus.DELIVERY;
            case "COMPLETE":
                return OrderStatus.COMPLETE;
            case "CANCEL":
                return OrderStatus.CANCEL;
        }

        return OrderStatus.CANCEL;
    }

    public DeliveryDto convertDeliveryDto(Orders order) {
        return DeliveryDto.builder()
                .orderId(order.getOrderId())
                .name(order.getReceiveName())
                .zipcode(order.getZipCode())
                .address(order.getAddress())
                .detailAddr(order.getExtraAddr())
                .build();
    }

    public Date dateFormat(Date date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date.toString());
    }


}
