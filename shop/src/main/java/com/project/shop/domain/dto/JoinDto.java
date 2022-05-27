package com.project.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {

    @NotNull
    private String memberId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String pw;

    @NotNull
    private String name;

    @NotNull
    private String nickname;

    @NotNull
    private int gender;

    @NotNull
    private String phone;

    @NotNull
    private Date birthday;

    @NotNull
    private String zipcode;

    @NotNull
    private String address;

    private String addrDetail;


}
