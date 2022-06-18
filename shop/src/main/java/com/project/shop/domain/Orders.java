package com.project.shop.domain;

import com.fasterxml.jackson.annotation.*;
import com.project.shop.domain.dto.DeliveryDto;
import com.project.shop.domain.dto.OrderDto;
import com.project.shop.domain.enums.OrderStatus;
import com.project.shop.domain.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", unique = true)
    private String orderId;

    private int cost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

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

    @Column(name = "delivery_message")
    private String deliveryMessage;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> order_items = new ArrayList<>();

    public Orders() {
    }

    private Orders(String orderId, int cost, String name, String zipCode, String address, String exrAddr, String deliveryMessage, PaymentMethod paymentMethod, Member member) {
        this.orderId = orderId;
        this.cost = cost;
        this.status = OrderStatus.ITEM_READY;
        this.createDate = new Date();
        this.updateDate = new Date();
        this.receiveName = name;
        this.zipCode = zipCode;
        this.address = address;
        this.extraAddr = exrAddr;
        this.deliveryMessage = deliveryMessage;
        this.paymentMethod = paymentMethod;
        this.member = member;
    }

    public static Orders createOrders(String orderId, OrderDto orderDto, PaymentMethod paymentMethod, Member member) {
        return new Orders(orderId, orderDto.getTotal(), orderDto.getReceiveName(), orderDto.getZipcode(), orderDto.getAddress(), orderDto.getExtraAddr(), orderDto.getDeliveryMessage(), paymentMethod, member);
    }


    public void orderStatesUpdate(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public void deliveryUpdate(DeliveryDto deliveryDto) {
        this.receiveName = deliveryDto.getName();
        this.zipCode = deliveryDto.getZipcode();
        this.address = deliveryDto.getAddress();
        this.extraAddr = deliveryDto.getDetailAddr();
    }
}
