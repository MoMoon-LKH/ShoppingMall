package com.project.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long itemId;

    private String name;

    private String imgUrl;

    private int cost;

    private int count;

    private String zipcode;

    private String address;

    private String extraAddr;

    private String paymentMethod;
}
