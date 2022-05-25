package com.project.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(name = "zip_code")
    private String zipCode;

    private String address;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public Delivery() {

    }

    private Delivery(String zipCode, String address, Member member) {
        this.zipCode = zipCode;
        this.address = address;
        this.member = member;
        this.createDate = new Date();
    }

    public static Delivery createDelivery(String zipCode, String address, Member member) {
        return new Delivery(zipCode, address, member);
    }
}
