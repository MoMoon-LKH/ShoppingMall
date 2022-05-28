package com.project.shop.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.shop.domain.enums.OrderStatus;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", unique = true)
    private String orderId;

    private int cost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "receive_name")
    private String receiveName;

    @Column(name = "zip_code")
    private String zipCode;

    private String address;

    @Column(name = "extra_addr")
    private String extraAddr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> order_items = new ArrayList<>();

    public Orders() {

    }

    private Orders(String orderId, int cost, OrderStatus orderStatus, String name, String zipCode, String address, String exrAddr, Member member) {
        this.orderId = orderId;
        this.cost = cost;
        this.status = orderStatus;
        this.createDate = new Date();
        this.updateDate = new Date();
        this.receiveName = name;
        this.zipCode = zipCode;
        this.address = address;
        this.extraAddr = exrAddr;
        this.member = member;
    }

    public static Orders createOrders() {
        return new Orders();
    }

}
