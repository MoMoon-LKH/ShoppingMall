package com.project.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

}
