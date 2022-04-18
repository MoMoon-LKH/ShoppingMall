package com.project.shop.entity;

import com.project.shop.entity.enums.OrderStatus;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Orders {

    @Id
    private Long id;

    private int count;

    private int cost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date create_date;

    private Date update_date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<Order_Item> order_items = new ArrayList<>();

}
