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

    @Column(name = "addr_detail")
    private String addrDetail;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public Delivery() {

    }

    private Delivery(String zipCode, String address, String addrDetail, Member member) {
        this.zipCode = zipCode;
        this.name = "기본배송지";
        this.address = address;
        this.member = member;
        this.addrDetail = addrDetail;
        this.createDate = new Date();
    }

    public static Delivery createDelivery(String zipCode, String address, String addrDetail, Member member) {
        return new Delivery(zipCode, address, addrDetail,member);
    }
}
