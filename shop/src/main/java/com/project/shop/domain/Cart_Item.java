package com.project.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Cart_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;


    public Cart_Item() {

    }

    private Cart_Item(int count, Item item, Cart cart){
        this.count = count;
        this.item = item;
        this.cart = cart;
    }

    public static Cart_Item createCart_Item(int count, Item item, Cart cart) {
        return new Cart_Item(count, item, cart);
    }
}
