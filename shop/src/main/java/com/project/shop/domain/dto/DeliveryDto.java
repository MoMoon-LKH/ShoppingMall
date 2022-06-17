package com.project.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private String orderId;
    private String zipcode;
    private String address;
    private String detailAddr;

}
