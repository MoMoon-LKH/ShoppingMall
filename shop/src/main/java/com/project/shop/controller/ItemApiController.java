package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.domain.dto.StockDto;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getItemsByMemberId(@PathVariable("memberId") Long memberId,
                                                @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(itemService.findByMemberId(memberId, pageable));
    }

    @GetMapping("total/{memberId}")
    public ResponseEntity<?> getTotalCount(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(itemService.getTotalCount(memberId));
    }

    @GetMapping("/item/list")
    public ResponseEntity<List<Item>> getItemList(
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable) {
        return ResponseEntity.ok(itemService.findAll(pageable));
    }

    @GetMapping("/item/list/{categoryId}")
    public ResponseEntity<List<Item>> getItemListByCategory(
            @PathVariable("categoryId") Long categoryId,
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable) {
        return ResponseEntity.ok(itemService.findByCategory(categoryId, pageable));
    }

    @PostMapping("/stock/add")
    public ResponseEntity<?> addStock(@Valid StockDto stockDto) {
        Item item = itemService.findById(stockDto.getItemId());
        return ResponseEntity.ok(itemService.addStock(item, stockDto.getCount()).getCount());
    }

    @PostMapping("/stock/remove")
    public ResponseEntity<?> removeStock(@Valid StockDto stockDto) {
        Item item = itemService.findById(stockDto.getItemId());
        return ResponseEntity.ok(itemService.removeItem(item, stockDto.getCount()).getCount());
    }
}
