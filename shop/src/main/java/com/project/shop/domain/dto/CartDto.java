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

    private Long cartItemId;

    @NotNull
    private Long itemId;

    @NotNull
    private String itemName;

    @NotNull
    private int itemCost;

    @NotNull
    private int count;

    private String itemImgUrl;

}
