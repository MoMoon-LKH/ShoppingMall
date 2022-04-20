package com.project.shop.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shop.entity.enums.Gender;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {

    private Long id;

    @NotNull
    private String memberId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String pw;

    @NotNull
    private String nickname;

    @NotNull
    private int gender;

    @NotNull
    private String phone;


    private Date birthday;


}
