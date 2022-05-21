package com.project.shop.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private Date createDate;


    @OneToMany(mappedBy = "cart")
    private List<Cart_Item> cart_items = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Cart() {

    }

    private Cart(Member member) {
        this.createDate = new Date();
        this.member = member;
    }

    public static Cart createCart( Member member) {
        return new Cart(member);
    }
}
