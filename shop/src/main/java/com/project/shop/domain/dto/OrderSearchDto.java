package com.project.shop.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderSearchDto {

    @Nullable
    @JsonProperty("search_word")
    private String searchWord;

    @Nullable
    @JsonProperty("start_day")
    private Date startDay;

    @Nullable
    @JsonProperty("end_day")
    private Date endDay;

}
