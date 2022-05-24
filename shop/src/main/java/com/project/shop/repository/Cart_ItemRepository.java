package com.project.shop.repository;

import com.project.shop.domain.Cart_Item;
import com.project.shop.domain.dto.CartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Cart_ItemRepository extends JpaRepository<Cart_Item, Long> {

    Optional<Cart_Item> findByItem_IdAndCart_Id(@Param("itemId") Long itemId, @Param("cartId") Long cartId);

    @Query("select new com.project.shop.domain.dto.CartDto(ci.id, i.id, i.name, i.cost, ci.count, i.imgUrl) from Cart_Item ci " +
            "join ci.item i " +
            "join ci.cart c " +
            "where c.id = :cartId")
    List<CartDto> findAllByCartId(@Param("cartId") Long cartId);
}
