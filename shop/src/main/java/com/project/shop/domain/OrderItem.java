package com.project.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.persistence.criteria.Order;

@Entity
@Getter
@Table(name = "OrderItem")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public OrderItem(){

    }

    private OrderItem(int count, Orders order, Item item) {
        this.count = count;
        this.order = order;
        this.item = item;
    }

    public static OrderItem createOrderItem(int count, Orders order, Item item) {
        return new OrderItem(count, order, item);
    }
}
