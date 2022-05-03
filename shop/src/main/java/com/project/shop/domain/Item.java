package com.project.shop.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.shop.domain.dto.ItemDto;
import com.project.shop.exceptions.NotEnoughItemException;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int count;

    private int cost;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "description_url")
    private String descriptionUrl;

    @Column(name = "etr_txt")
    private String etrTxt;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @OneToMany(mappedBy = "item")
    private List<Order_Item> order_items = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Cart_Item> cart_items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    public Item() {
    }

    private Item(ItemDto itemDto, Member member, Category category, String imgUrl, String descriptionUrl) {
        this.name = itemDto.getName();
        this.count = itemDto.getCount();
        this.cost = itemDto.getCost();
        this.imgUrl = imgUrl;
        this.descriptionUrl = descriptionUrl;
        this.etrTxt = itemDto.getEtrTxt();
        this.createDate = new Date();
        this.updateDate = new Date();
        this.member = member;
        this.category = category;
    }

    public static Item createItem(ItemDto itemDto, Member member, Category category ,String imgUrl, String descriptionUrl) {
        return new Item(itemDto, member, category, imgUrl, descriptionUrl);
    }


    public void update(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.count = itemDto.getCount();
        this.cost = itemDto.getCost();
        this.imgUrl = itemDto.getImgUrl().getName();
        this.descriptionUrl = itemDto.getDescriptionUrl().getName();
        this.etrTxt = itemDto.getEtrTxt();
        this.updateDate = new Date();
    }

    public void addCount(int count) {
        this.count += count;
    }


    public void removeCount(int orderCount) {
        int result = this.count - orderCount;
        if (result < 0) {
            throw new NotEnoughItemException();
        }

        this.count = result;
    }


}
