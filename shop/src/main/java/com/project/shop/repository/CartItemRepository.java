package com.project.shop.repository;

import com.project.shop.domain.CartItem;
import com.project.shop.domain.dto.CartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByItem_IdAndCart_Id(@Param("itemId") Long itemId, @Param("cartId") Long cartId);

    @Query("select new com.project.shop.domain.dto.CartDto(ci.id, i.id, i.name, i.cost, ci.count, i.imgUrl) from CartItem ci " +
            "join ci.item i " +
            "join ci.cart c " +
            "where c.id = :cartId")
    List<CartDto> findAllByCartId(@Param("cartId") Long cartId);


    @Query("select new com.project.shop.domain.dto.CartDto(ci.id, i.id, i.name, i.cost, ci.count, i.imgUrl) from CartItem ci " +
            "join ci.item i " +
            "join ci.cart c " +
            "where ci.id = :id")
    Optional<CartDto> findCartDtoByCartItemId(@Param("id") Long id);

}
