package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/{itemId}")
    public String getItemList(@PathVariable("itemId") Long itemId, Model model) {
        model.addAttribute("item", itemService.findById(itemId));
        return "/item/itemInfo";
    }

}
