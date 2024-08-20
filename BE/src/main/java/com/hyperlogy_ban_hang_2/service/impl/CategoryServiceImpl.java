package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.CategoryDto;
import com.hyperlogy_ban_hang_2.entity.Category;
import com.hyperlogy_ban_hang_2.mapper.CategoryMapper;
import com.hyperlogy_ban_hang_2.repository.CategoryRepository;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements GeneralService<CategoryDto> {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(category -> categoryMapper.mapCateToCateDto(category)).toList();
    }

    @Override
    public String saveOrUpdate(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.mapCateDtoToCate(categoryDto));
        return "Thanh cong";
    }

    @Override
    public CategoryDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            return categoryMapper.mapCateToCateDto(category);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            categoryRepository.delete(category);
        }
        return "Da xoa";
    }
}
