package com.project.shop.repository;

import com.project.shop.domain.QOrderItem;
import com.project.shop.domain.QOrders;
import com.project.shop.domain.dto.OrderDto;
import com.project.shop.domain.dto.OrderListDto;
import com.project.shop.domain.dto.OrderSearchDto;
import com.project.shop.domain.dto.QOrderListDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderListDto> findAllOrderBySearch(OrderSearchDto orderSearchDto, Pageable pageable) {
        QOrders order = QOrders.orders;

        BooleanBuilder builder = createSearchWhere(orderSearchDto);

        return jpaQueryFactory
                .select(new QOrderListDto(
                        order.id,
                        order.orderId,
                        order.orderId,
                        order.orderId,
                        order.order_items.size(),
                        order.cost,
                        order.createDate,
                        order.status
                ))
                .from(order)
                .where(
                    builder
                )
                .offset((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countOrderBySearch(OrderSearchDto orderSearchDto) {
        QOrders order = QOrders.orders;
        BooleanBuilder builder = createSearchWhere(orderSearchDto);

        return (long) jpaQueryFactory
                .select(new QOrderListDto(
                        order.id,
                        order.orderId,
                        order.orderId,
                        order.orderId,
                        order.order_items.size(),
                        order.cost,
                        order.createDate,
                        order.status
                ))
                .from(order)
                .where(
                        builder
                )
                .fetch().size();
    }


    public BooleanBuilder createSearchWhere(OrderSearchDto orderSearchDto) {
        QOrders order = QOrders.orders;
        BooleanBuilder builder = new BooleanBuilder();

        if (orderSearchDto.getSearchWord() != null) {
            builder.and(
                    order.orderId.startsWith(orderSearchDto.getSearchWord())
            );

        }
        if(orderSearchDto.getStartDate() != null)
            builder.and(order.createDate.goe(orderSearchDto.getStartDate()));

        if(orderSearchDto.getEndDate() != null)
            builder.and(order.createDate.loe(orderSearchDto.getEndDate()));

        return builder;
    }
}
