package com.project.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long orderItemId;

    private Long itemId;

    private String name;

    private String imgUrl;

    private int cost;

    private int count;
}
