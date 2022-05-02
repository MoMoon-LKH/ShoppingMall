package com.project.shop.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
public class ItemDto {

    private Long id;

    private String name;

    private int count;

    private int cost;

    private String imgUrl;

    private String descriptionUrl;

    private String etrTxt;

    private Date createDate;

    private Date updateDate;
}
