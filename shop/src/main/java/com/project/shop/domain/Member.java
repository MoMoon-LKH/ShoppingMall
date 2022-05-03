package com.project.shop.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.shop.domain.dto.JoinDto;
import com.project.shop.domain.enums.Gender;
import com.project.shop.domain.enums.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberId;


    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
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

    @ManyToMany
    @JoinTable(
            name = "member_authoriy",
            joinColumns = {@JoinColumn(name = "memberId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authoriyId", referencedColumnName = "id")}
    )
    private Set<Authority> authorities;

    private Member(String memberId, String password, String nickname, Gender gender, String phone, Date birthday, Authority authority) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.birthday = birthday;
        this.createDate = new Date();
        this.updateDate = new Date();
        this.status = MemberStatus.ALIVE;
        this.authorities = Collections.singleton(authority);

    }


    public static Member createMember(JoinDto joinDto, Authority authority) {
        Gender gender;

        if (joinDto.getGender() == 0) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        return new Member(joinDto.getMemberId(), joinDto.getPw(), joinDto.getNickname(), gender, joinDto.getPhone(), joinDto.getBirthday(), authority);
    }

}
