package com.project.shop.controller;

import com.project.shop.domain.Cart;
import com.project.shop.domain.Cart_Item;
import com.project.shop.domain.Item;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.service.CartService;
import com.project.shop.service.ItemService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final ItemService itemService;


    @GetMapping("/{memberId}")
    public ResponseEntity<List<CartDto>> getCartList(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(cartService.findByMemberId(memberId));
    }


    @PostMapping("/item/new")
    public ResponseEntity<?> saveCart(@RequestBody CartDto cartDto) {
        Cart cart = cartService.findByCart(cartDto.getMemberId());
        Item item = itemService.findById(cartDto.getItemId());
        Cart_Item cart_item = Cart_Item.createCart_Item(cartDto.getCount(), item, cart);

        return ResponseEntity.ok(cartService.cart_itemSave(cart_item));
    }


    @PostMapping("/item/deletes")
    public ResponseEntity<?> deleteCartItems(@RequestBody List<Long> cartItemIds) {
        List<Cart_Item> cartItems = cartService.findAllByCartItemId(cartItemIds);

        return ResponseEntity.ok(cartService.delete_cartItems(cartItems));
    }


}
