package com.project.shop.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    private int cost;

    private Date create_date;


    @OneToMany(mappedBy = "cart")
    private List<Cart_Item> cart_items = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
