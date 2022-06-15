package com.project.shop.repository;

import com.project.shop.domain.dto.OrderListDto;
import com.project.shop.domain.dto.OrderSearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderListDto> findAllOrderBySearch(OrderSearchDto orderSearchDto, Pageable pageable);

    Long countOrderBySearch(OrderSearchDto orderSearchDto);
}
