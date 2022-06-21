package com.project.shop.service;


import com.project.shop.domain.Item;
import com.project.shop.domain.dto.ItemDto;
import com.project.shop.exceptions.NoSuchItemException;
import com.project.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(Item item) {
        return itemRepository.save(item).getId();
    }

    @Transactional
    public boolean update(Item item ,ItemDto itemdto, String img, String descriptionImg) {
        try {
            item.update(itemdto, img, descriptionImg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean delete(Item item) {
        try {
            itemRepository.delete(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public Item addStock(Item item, int count) {
        item.addCount(count);
        return item;
    }

    @Transactional
    public Item removeStock(Item item, int count) {
        item.removeCount(count);
        return item;
    }

    @Transactional
    public void removeItem(Item item, int count) {
        item.removeCount(count);
    }

    @Transactional
    public Item updateStock(Item item, int count) {
        item.updateCount(count);
        return item;
    }

    public List<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable).getContent();
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);
    }

    public List<Item> findByMemberId(Long memberId , Pageable pageable) {
        return itemRepository.findAllByMember_Id(memberId, pageable);
    }

    public List<Item> findByCategory(Long categoryId, Pageable pageable) {
        return itemRepository.findAllByCategory_Id(categoryId, pageable);
    }


    public Integer getTotalCount() {
        return itemRepository.countAllBy().intValue();
    }

    public Integer getCategoryTotal(Long categoryId) {
        return itemRepository.countAllByCategory_Id(categoryId).intValue();
    }

    public String getImgUrlByItemName(String itemName) {
        return itemRepository.getImgUrlByItemName(itemName);
    }






}
