package com.project.shop.service;

import com.project.shop.domain.Category;
import com.project.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchFieldError::new);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(NoSuchFieldError::new);
    }
}
