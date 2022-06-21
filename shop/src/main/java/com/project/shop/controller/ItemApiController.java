package com.project.shop.controller;

import com.project.shop.domain.Item;
import com.project.shop.domain.dto.ItemDto;
import com.project.shop.domain.dto.StockDto;
import com.project.shop.service.ImageService;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemApiController {

    private final ItemService itemService;

    private ImageService imageService;


    @GetMapping("/{memberId}")
    public ResponseEntity<?> getItemsByMemberId(@PathVariable("memberId") Long memberId,
                                                @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(itemService.findByMemberId(memberId, pageable));
    }


    @GetMapping("/total")
    public ResponseEntity<?> getTotal() {
        return ResponseEntity.ok(itemService.getTotalCount());
    }



    @GetMapping("/category/total/{categoryId}")
    public ResponseEntity<?> getCategoryTotal(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(itemService.getCategoryTotal(categoryId));
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<Item>> getItemList(
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(itemService.findAll(pageable));
    }



    @GetMapping("/category/list/{categoryId}")
    public ResponseEntity<List<Item>> getItemListByCategory(
            @PathVariable("categoryId") Long categoryId,
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable) {
        return ResponseEntity.ok(itemService.findByCategory(categoryId, pageable));
    }


    @PostMapping("/stock/add")
    public ResponseEntity<?> addStock(@Valid @RequestBody StockDto stockDto) {

        Item item = itemService.findById(stockDto.getItemId());
        return ResponseEntity.ok(itemService.addStock(item, stockDto.getCount()).getCount());
    }


    @PostMapping("/stock/remove")
    public ResponseEntity<?> removeStock(@Valid @RequestBody StockDto stockDto) {
        Item item = itemService.findById(stockDto.getItemId());
        return ResponseEntity.ok(itemService.removeStock(item, stockDto.getCount()).getCount());
    }

    @PostMapping("/stock/update")
    public ResponseEntity<?> updateStock(@Valid @RequestBody StockDto stockDto) {
        Item item = itemService.findById(stockDto.getItemId());
        return ResponseEntity.ok(itemService.updateStock(item, stockDto.getCount()).getCount());
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateItem(@Valid @RequestBody ItemDto itemDto) throws IOException {
        String img = "";
        String descriptionImg = "";
        int ran = (int) (Math.random()*10000);

        Item item = itemService.findById(itemDto.getId());

        if (itemDto.getImgUrl() != null) {
            imageService.imgDelete(item.getImgUrl());
            img = imageService.transferImg(itemDto.getImgUrl(), ran, 0);
        }

        if (itemDto.getDescriptionUrl() != null) {
            imageService.imgDelete(item.getDescriptionUrl());
            descriptionImg = imageService.transferImg(itemDto.getDescriptionUrl(), ran, 1);
        }

        itemService.update(item, itemDto, img, descriptionImg);

        return ResponseEntity.ok(
                ItemDto.builder()
                        .name(item.getName())
                        .cost(item.getCost())
                        .build()
        );
    }

}
