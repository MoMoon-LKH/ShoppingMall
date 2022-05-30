package com.project.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private String orderId;

    @NotNull
    private int total;

    @NotNull
    private String receiveName;

    @NotNull
    private String zipcode;

    @NotNull
    private String address;

    private String extraAddr;


    private List<CartDto> cartDtos = new ArrayList<>();

}
