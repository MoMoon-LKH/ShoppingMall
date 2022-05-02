package com.project.shop.controller;

import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemApiController {

    private final ItemService itemService;


    @GetMapping("/{memberId}")
    public ResponseEntity<?> getItemsByMemberId(@PathVariable("memberId") Long memberId) {

        return ResponseEntity.ok("");
    }



}
