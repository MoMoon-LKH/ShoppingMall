package com.project.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    @NotEmpty
    private String orderId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String zipcode;

    @NotEmpty
    private String address;

    @NotEmpty
    @JsonProperty("detail_addr")
    private String detailAddr;

}
