package com.project.shop.controller;

import com.project.shop.domain.Category;
import com.project.shop.domain.Item;
import com.project.shop.domain.Member;
import com.project.shop.domain.dto.ItemDto;
import com.project.shop.domain.dto.StockDto;
import com.project.shop.domain.userDetails.Account;
import com.project.shop.service.CategoryService;
import com.project.shop.service.ItemService;
import com.project.shop.service.MemberService;
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


    @Value("${imagePath}")
    private String imgPath;

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

    @PostMapping("/item/new")
    public String registerItem(@Valid ItemDto itemDto, BindingResult bindingResult, @AuthenticationPrincipal Account account) throws IOException {

        String imgUrl = " ";
        String descriptionImg = " ";
        int ran = (int) (Math.random()*10000);


        if(!itemDto.getImgUrl().isEmpty())
            imgUrl = transferImg(itemDto.getImgUrl(), ran,0);

        if(!itemDto.getDescriptionUrl().isEmpty())
            descriptionImg = transferImg(itemDto.getDescriptionUrl(), ran, 1);

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


    @PostMapping("/item/update") //api 고려
    public String updateItem(@Valid ItemDto itemDto) throws IOException{
        String img = "";
        String descriptionImg = "";
        int ran = (int) (Math.random()*10000);

        Item item = itemService.findById(itemDto.getId());

        if (!itemDto.getImgUrl().isEmpty()) {
            imgDelete(item.getImgUrl());
            img = transferImg(itemDto.getImgUrl(), ran, 0);
        }

        if (!itemDto.getDescriptionUrl().isEmpty()) {
            imgDelete(item.getDescriptionUrl());
            descriptionImg = transferImg(itemDto.getDescriptionUrl(), ran, 1);
        }

        boolean update = itemService.update(item, img, descriptionImg);

        return "redirect:/seller/sellItemInfo";
    }




    public String transferImg(MultipartFile file, int ran, int type) throws IOException {

        String originName = file.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf("."));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");


        String fname = "";


        if (type == 0) {
            fname = dateFormat.format(new Date()) + "_" + ran + ext;
        } else {
            fname = dateFormat.format(new Date()) + "_" + ran + "d" + ext;
        }
        file.transferTo(new File(imgPath + "/" + fname));

        return fname;
    }

    public void imgDelete(String img) {
        if (!imgPath.isEmpty()) {
            File file = new File(imgPath + img);

            if (file.exists()) {
                file.delete();
            }
        }
    }


}
