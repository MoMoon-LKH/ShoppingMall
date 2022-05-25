package com.project.shop.controller;

import com.project.shop.domain.Cart;
import com.project.shop.domain.Cart_Item;
import com.project.shop.domain.Item;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.CartService;
import com.project.shop.service.ItemService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final ItemService itemService;


    @GetMapping("/list")
    public ResponseEntity<List<CartDto>> getCartList(@AuthenticationPrincipal Account account) {
        Cart cart = cartService.findCartByMemberId(account.getId());
        return ResponseEntity.ok(cartService.findCartItemAllByCartId(cart.getId()));
    }


    @PostMapping("/item/new")
    public ResponseEntity<?> saveCartItem(@RequestBody CartDto cartDto, @AuthenticationPrincipal Account account) {
        Cart cart = cartService.findCartByMemberId(account.getId());
        Item item = itemService.findById(cartDto.getItemId());
        Optional<Cart_Item> cartItem = cartService.findByItemIdAndCartId(item.getId(), cart.getId());

        if (cartItem.isPresent()) {
            cartService.addCount(cartItem.get(), cartDto.getCount());
            return ResponseEntity.ok(cartItem.get().getCount());
        } else{
            Cart_Item newCartItem = Cart_Item.createCart_Item(cartDto.getCount(), item, cart);

            return ResponseEntity.ok(cartService.cart_itemSave(newCartItem));
        }
    }


    @PostMapping("/item/deletes")
    public ResponseEntity<?> deleteCartItems(@RequestBody List<Long> cartItemIds, @AuthenticationPrincipal Account account) {
        List<Cart_Item> cartItems = cartService.findAllByCartItemId(cartItemIds);
        boolean bool = cartService.delete_cartItems(cartItems);

        if (bool) {
            Cart cart = cartService.findCartByMemberId(account.getId());
            return ResponseEntity.ok(cartService.findCartItemAllByCartId(cart.getId()));
        }

        return ResponseEntity.ok(bool);
    }


}
