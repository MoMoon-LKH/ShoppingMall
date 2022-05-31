package com.project.shop.controller;

import com.project.shop.domain.*;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.domain.dto.OrderDto;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final CartService cartService;
    private final MemberService memberService;
    private final DeliveryService deliveryService;
    private final OrderService orderService;

    private final ItemService itemService;


    @GetMapping("/form")
    public String orderPage(@RequestParam List<Long> item, @AuthenticationPrincipal Account account, Model model) {

        int total = 0;

        List<CartDto> cartDtos = cartService.findAllCartDtoByCartItemId(item);

        model.addAttribute("items", cartDtos);
        model.addAttribute("member", memberService.findById(account.getId()));
        model.addAttribute("delivery", deliveryService.findAllByMemberId(account.getId()));

        for (CartDto cartDto : cartDtos)
            total += cartDto.getCount() * cartDto.getItemCost();

        model.addAttribute("total", total);

        return "/member/order";
    }


    @GetMapping("/purchase")
    public String purchaseOrderPage(CartDto cartDto, @AuthenticationPrincipal Account account, Model model) {

        int total = 0;

        Item item = itemService.findById(cartDto.getItemId());

        List<CartDto> cartDtos = new ArrayList<>();

        cartDtos.add(CartDto.builder()
                .itemId(item.getId())
                .count(cartDto.getCount())
                .itemCost(item.getCost())
                .itemImgUrl(item.getImgUrl())
                .itemName(item.getName())
                .build());

        model.addAttribute("items", cartDtos);
        model.addAttribute("member", memberService.findById(account.getId()));
        model.addAttribute("delivery", deliveryService.findAllByMemberId(account.getId()));

        total += cartDtos.get(0).getCount() * cartDtos.get(0).getItemCost();

        model.addAttribute("total", total);

        return "/member/purchaseOrder";
    }


    @GetMapping("/successPage/{orderNumber}")
    public String successPage(@PathVariable("orderNumber") String orderNumber, @AuthenticationPrincipal Account account, Model model) {

        model.addAttribute("orderNum", orderNumber);
        model.addAttribute("memberId", account.getId());
        return "/member/orderSuccess";
    }


    @ResponseBody
    @PostMapping("/cart/addOrder")
    public ResponseEntity<?> AddOrderByCart(@Valid @RequestBody OrderDto orderDto, @AuthenticationPrincipal Account account) {

        List<CartItem> cartItems = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();

        Member member = memberService.findById(account.getId());
        Cart cart = cartService.findCartByMemberId(member.getId());
        Orders order = orderService.saveOrder(Orders.createOrders(createOrderId(), orderDto, member));

        for (CartDto cartDto : orderDto.getCartDtos()) {
            Optional<CartItem> cartItem = cartService.findByItemIdAndCartId(cartDto.getItemId(), cart.getId());

            if (cartItem.isPresent()) {
                Item item = cartItem.get().getItem();
                OrderItem orderItem = OrderItem.createOrderItem(cartItem.get().getCount(), order, item);

                itemService.removeItem(item, orderItem.getCount());

                cartItems.add(cartItem.get());
                orderItems.add(orderItem);
            }

        }


        Map<String, Object> map = new HashMap<>();
        map.put("orderNum", order.getOrderId());
        map.put("savedOrderItemCount", orderService.saveOrderItems(orderItems));
        map.put("delBool", cartService.deleteCartItems(cartItems));

        return ResponseEntity.ok("map");
    }


    @ResponseBody
    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderDto orderDto, @AuthenticationPrincipal Account account) {

        List<OrderItem> orderItems = new ArrayList<>();

        Member member = memberService.findById(account.getId());
        Orders order = orderService.saveOrder(Orders.createOrders(createOrderId(), orderDto, member));

        for (CartDto cartDto : orderDto.getCartDtos()) {

            OrderItem orderItem = OrderItem.createOrderItem(cartDto.getCount(), order, itemService.findById(cartDto.getItemId()));
            orderItems.add(orderItem);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("orderNum", order.getOrderId());
        map.put("savedOrderItemCount", orderService.saveOrderItems(orderItems));

        return ResponseEntity.ok(map);
    }



    public String createOrderId() {
        int ran = (int) (Math.random() * 100000000);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());

        return date + ran;
    }

    /*@ResponseBody
    @GetMapping("/member/addresses")
    public ResponseEntity<List<Delivery>> getAddresses(@AuthenticationPrincipal Account account) {
        return ResponseEntity.ok(deliveryService.findAllByMemberId(account.getId()));
    }*/

}
