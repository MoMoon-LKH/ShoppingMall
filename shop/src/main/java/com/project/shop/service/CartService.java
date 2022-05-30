package com.project.shop.service;


import com.project.shop.domain.Cart;
import com.project.shop.domain.CartItem;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.repository.CartRepository;
import com.project.shop.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cart_itemRepository;


    @Transactional
    public Long save(Cart cart) {
        return cartRepository.save(cart).getId();
    }

    @Transactional
    public Long cartItemSave(CartItem cart_item) {
        return cart_itemRepository.save(cart_item).getId();
    }

    @Transactional
    public boolean deleteCartItems(List<CartItem> cart_item) {
        try {
            cart_itemRepository.deleteAll(cart_item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void addCount(CartItem cart_item, int count) {
        cart_item.addCount(count);
    }

    public Cart findCartByMemberId(Long memberId) {
        return cartRepository.findByMember_Id(memberId).orElseThrow(NoSuchFieldError::new);
    }

    public Optional<CartItem> findByCartItemId(Long cartItemId) {
        return cart_itemRepository.findById(cartItemId);
    }

    public Optional<CartItem> findByItemIdAndCartId(Long itemId, Long cartId) {
        return cart_itemRepository.findByItem_IdAndCart_Id(itemId, cartId);
    }



    public List<CartDto> findCartItemAllByCartId(Long cartId) {
        return cart_itemRepository.findAllByCartId(cartId);
    }


    public List<CartItem> findAllByCartItemId(List<Long> cartItemIds) {
        return cart_itemRepository.findAllById(cartItemIds);
    }

    public List<CartDto> findAllCartDtoByCartItemId(List<Long> ids) {
        List<CartDto> cartList = new ArrayList<>();

        for (Long id : ids) {
            Optional<CartDto> item = cart_itemRepository.findCartDtoByCartItemId(id);

            if (item.isPresent()) {
                cartList.add(item.get());
            }
        }

        return cartList;
    }



}
