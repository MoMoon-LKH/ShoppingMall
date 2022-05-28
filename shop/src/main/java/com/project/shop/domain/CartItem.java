package com.project.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Cart_Item")
public class CartItem {

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


    public CartItem() {

    }

    private CartItem(int count, Item item, Cart cart){
        this.count = count;
        this.item = item;
        this.cart = cart;
    }

    public static CartItem createCart_Item(int count, Item item, Cart cart) {
        return new CartItem(count, item, cart);
    }

    public void addCount(int count) {
        this.count += count;
    }
}
