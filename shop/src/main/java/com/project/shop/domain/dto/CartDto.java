package com.project.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long CartItemId;

    @NotNull
    private Long itemId;

    private String itemName;

    private int itemCost;

    @NotNull
    private int count;

    private String itemImgUrl;

    private Long memberId;
}
