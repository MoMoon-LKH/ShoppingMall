package com.project.shop.controller;

import com.project.shop.domain.*;
import com.project.shop.domain.dto.CartDto;
import com.project.shop.domain.dto.OrderDto;
import com.project.shop.domain.dto.OrderItemDto;
import com.project.shop.domain.dto.OrderListDto;
import com.project.shop.domain.enums.PaymentMethod;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

        model.addAttribute("orderId", orderNumber);
        model.addAttribute("memberId", account.getId());
        return "/member/orderSuccess";
    }

    @GetMapping("/info")
    public String orderInfoPage(String orderId, @AuthenticationPrincipal Account account, Model model) {

        Member member = memberService.findById(account.getId());
        Orders order = orderService.findOrderByOrderId(orderId);
        List<OrderItemDto> orderItems = orderService.findOrderItemByOrderId(orderId);

        model.addAttribute("member", member);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);

        return "/member/orderInfo";
    }

    @GetMapping("/my")
    public String mypage() {
        return "/member/myOrders";
    }

    @ResponseBody
    @PostMapping("/cart/addOrder")
    public ResponseEntity<?> AddOrderByCart(@Valid @RequestBody OrderDto orderDto, @AuthenticationPrincipal Account account) {

        List<CartItem> cartItems = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();

        Member member = memberService.findById(account.getId());
        Cart cart = cartService.findCartByMemberId(member.getId());
        PaymentMethod paymentMethod;

        if (orderDto.getPaymentMethod().equals("CARD")) {
            paymentMethod = PaymentMethod.CARD;
        } else {
            paymentMethod = PaymentMethod.PASSBOOK;
        }

        Orders order = orderService.saveOrder(Orders.createOrders(createOrderId(), orderDto, paymentMethod, member));

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

        return ResponseEntity.ok(map);
    }


    @ResponseBody
    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderDto orderDto, @AuthenticationPrincipal Account account) {

        List<OrderItem> orderItems = new ArrayList<>();

        PaymentMethod paymentMethod;

        if (orderDto.getPaymentMethod().equals("CARD")) {
            paymentMethod = PaymentMethod.CARD;
        } else {
            paymentMethod = PaymentMethod.PASSBOOK;
        }

        Member member = memberService.findById(account.getId());
        Orders order = orderService.saveOrder(Orders.createOrders(createOrderId(), orderDto, paymentMethod, member));

        for (CartDto cartDto : orderDto.getCartDtos()) {
            OrderItem orderItem = OrderItem.createOrderItem(cartDto.getCount(), order, itemService.findById(cartDto.getItemId()));
            orderItems.add(orderItem);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("orderNum", order.getOrderId());
        map.put("savedOrderItemCount", orderService.saveOrderItems(orderItems));

        return ResponseEntity.ok(map);
    }


    @ResponseBody
    @GetMapping("/my/list")
    public ResponseEntity<?> getMyOrderList(
            @AuthenticationPrincipal Account account,
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC, size = 5) Pageable pageable) {


        List<OrderListDto> orderList = orderService.findOrderListByMemberId(account.getId(), pageable);

        for (OrderListDto order : orderList) {
            order.setItemName(orderService.findItemNameByOrder_Id(order.getId()));
            order.setImgUrl(itemService.getImgUrlByItemName(order.getItemName()));

        }

        return ResponseEntity.ok(orderList);
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
