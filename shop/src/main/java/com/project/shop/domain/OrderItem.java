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

    private int cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public OrderItem(){

    }

    private OrderItem(int count, int cost, Orders order, Item item) {
        this.count = count;
        this.cost = cost;
        this.order = order;
        this.item = item;
    }

    public static OrderItem createOrderItem(int count, int cost, Orders order, Item item) {
        return new OrderItem(count, cost, order, item);
    }
}
