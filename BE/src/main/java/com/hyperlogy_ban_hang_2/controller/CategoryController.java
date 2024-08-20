package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.CategoryDto;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {
    private final GeneralService<CategoryDto> categoryService;
    private List<CategoryDto> listCategory = new ArrayList<>();


    @PostMapping("/add")
    public void add(@RequestBody CategoryDto categoryDto) {
        String add = categoryService.saveOrUpdate(categoryDto);
    }

    @GetMapping("/get-all")
    public List<CategoryDto> getAllCategory() {
        listCategory = categoryService.getAll();
        return listCategory;
    }

    @PutMapping("/update")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.saveOrUpdate(categoryDto);
        return categoryDto;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        String delete = categoryService.delete(id);
    }

    @GetMapping("/get-one/{id}")
    public CategoryDto getOneCategory(@PathVariable("id") Long id) {
        return categoryService.getOne(id);
    }
}
