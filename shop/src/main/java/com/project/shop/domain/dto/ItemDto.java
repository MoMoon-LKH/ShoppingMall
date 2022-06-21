package com.project.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int count;

    @NotNull
    private int cost;

    private MultipartFile imgUrl;

    private MultipartFile descriptionUrl;

    private String etrTxt;

    private Date createDate;

    private Date updateDate;

    private Long categoryId;


}
