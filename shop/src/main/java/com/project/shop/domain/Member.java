package com.project.shop.domain;

import com.project.shop.domain.dto.JoinDto;
import com.project.shop.domain.enums.Gender;
import com.project.shop.domain.enums.MemberStatus;
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


    private Member(String memberId, String password, String nickname, Gender gender, String phone, Date birthday) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.birthday = birthday;
        this.createDate = new Date();
        this.updateDate = new Date();
        this.status = MemberStatus.ALIVE;
    }

    public Member() {

    }

    public static Member createMember(JoinDto joinDto) {
        Gender gender;

        if (joinDto.getGender() == 0) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        return new Member(joinDto.getMemberId(), joinDto.getPw(), joinDto.getNickname(), gender, joinDto.getPhone(), joinDto.getBirthday());
    }

}
