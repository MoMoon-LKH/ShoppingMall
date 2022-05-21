package com.project.shop.repository;

import com.project.shop.domain.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cart_ItemRepository extends JpaRepository<Cart_Item, Long> {


}
