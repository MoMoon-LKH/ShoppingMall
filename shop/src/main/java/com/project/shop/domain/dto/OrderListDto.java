package com.project.shop.domain.dto;

import com.project.shop.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDto {

    private Long id;

    private String orderId;

    private String itemName;

    private String imgUrl;

    private int itemCount;

    private int total;

    private Date createDate;


    private OrderStatus orderState;
}
