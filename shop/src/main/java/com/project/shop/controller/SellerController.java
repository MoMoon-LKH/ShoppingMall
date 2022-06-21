package com.project.shop.controller;

import com.project.shop.domain.Category;
import com.project.shop.domain.Item;
import com.project.shop.domain.Member;
import com.project.shop.domain.dto.ItemDto;
import com.project.shop.domain.dto.StockDto;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sell")
public class SellerController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final OrderService orderService;

    private ImageService imageService;


    @GetMapping("/my")
    public String sellMain(Model model) {

        return "/seller/mySell";
    }


    @GetMapping("/my/item")
    public String itemManagement() {
        return "/seller/itemManage";
    }

    @GetMapping("/my/order")
    public String orderManagement() {
        return "/seller/orderManage";
    }

    @GetMapping("/item/new")
    public String registerPage(@AuthenticationPrincipal Account account, Model model) {

        model.addAttribute("id", account.getId());
        model.addAttribute("itemDto", new ItemDto());
        model.addAttribute("category", categoryService.findAll());
        return "/seller/registerItem";
    }


    @GetMapping("/order/manage/{orderId}")
    public String orderManagePage(@PathVariable String orderId, Model model) {

        model.addAttribute("order", orderService.findOrderByOrderId(orderId));

        return "/seller/orderManagePage";
    }

    @PostMapping("/item/new")
    public String registerItem(@Valid ItemDto itemDto, BindingResult bindingResult, @AuthenticationPrincipal Account account) throws IOException {

        String imgUrl = " ";
        String descriptionImg = " ";
        int ran = (int) (Math.random()*10000);


        if(!itemDto.getImgUrl().isEmpty())
            imgUrl = imageService.transferImg(itemDto.getImgUrl(), ran,0);

        if(!itemDto.getDescriptionUrl().isEmpty())
            descriptionImg = imageService.transferImg(itemDto.getDescriptionUrl(), ran, 1);

        Member member = memberService.findById(account.getId());
        Category category = categoryService.findById(itemDto.getCategoryId());

        Item item = Item.createItem(itemDto, member, category, imgUrl, descriptionImg);

        itemService.save(item);

        return "redirect:/sell/my";
    }


    @GetMapping("/item/{itemId}")
    public String getItem(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findById(itemId);

        model.addAttribute("item", item);
        model.addAttribute("stock", new StockDto());

        return "/seller/sellItemInfo";
    }



}
