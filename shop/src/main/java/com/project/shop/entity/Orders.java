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

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<Order_Item> order_items = new ArrayList<>();

}