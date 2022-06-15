package com.project.shop.domain.dto;

import com.project.shop.domain.enums.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
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

    @QueryProjection
    public OrderListDto(Long id, String orderId, String itemName, String imgUrl, int itemCount, int total, Date createDate, OrderStatus orderStatus) {
        this.id = id;
        this.orderId = orderId;
        this.itemName = itemName;
        this.imgUrl = imgUrl;
        this.itemCount = itemCount;
        this.total = total;
        this.createDate = createDate;
        this.orderState = orderStatus;
    }


}
