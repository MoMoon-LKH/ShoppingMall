package com.project.shop.service;


import com.project.shop.domain.Item;
import com.project.shop.domain.dto.ItemDto;
import com.project.shop.exceptions.NoSuchItemException;
import com.project.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean update(ItemDto itemDto) {
        try {
            Item item = itemRepository.findById(itemDto.getId()).orElseThrow(NoSuchItemException::new);
            item.update(itemDto);
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
    public Item purchaseItem(Item item, int count) {
        item.removeCount(count);
        return item;
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);
    }

    public List<Item> findByMemberId(Long memberId , Pageable pageable) {
        return itemRepository.findByMember_MemberIdOrderByCreateDateDesc(memberId, pageable);
    }

    public List<Item> findByCategory(Long categoryId, Pageable pageable) {
        return itemRepository.findByCategory_IdOrderByCreateDateDesc(categoryId, pageable);
    }


}
