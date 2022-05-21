package com.project.shop.service;


import com.project.shop.domain.Cart;
import com.project.shop.domain.Cart_Item;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.repository.CartRepository;
import com.project.shop.repository.Cart_ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final Cart_ItemRepository cart_itemRepository;

    @Transactional
    public Long save(Cart cart) {
        return cartRepository.save(cart).getId();
    }

    @Transactional
    public Long cart_itemSave(Cart_Item cart_item) {
        return cart_itemRepository.save(cart_item).getId();
    }


    public Cart findByCart(Long memberId) {
        return cartRepository.findByMember_Id(memberId).orElseThrow(NoSuchFieldError::new);
    }


    public List<CartDto> findByMemberId(Long memberId) {
        return cartRepository.findAllByCartItems(memberId);
    }



}
