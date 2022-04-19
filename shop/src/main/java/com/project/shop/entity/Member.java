package com.project.shop.entity;

import com.project.shop.entity.enums.Gender;
import com.project.shop.entity.enums.MemberStatus;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberId;

    private String password;

    private String nickname;

    private Gender gender;

    private String phone;

    private Date birthday;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;


    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Delivery> deliveries = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cart> carts = new ArrayList<>();

}
