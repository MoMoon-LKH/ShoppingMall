package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getItemsByMemberId(@PathVariable("memberId") Long memberId,
                                                @PageableDefault(size = 10, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(itemService.findByMemberId(memberId, pageable));
    }

    @GetMapping("total/{memberId}")
    public ResponseEntity<?> getTotalCount(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(itemService.getTotalCount(memberId));
    }



}
