package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.ProductDto;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/product")
@CrossOrigin(origins = {"*"})
public class ProductController {
    private final GeneralService<ProductDto> productService;

    @GetMapping("/get-all")
    private List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/get-one/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) {
        return productService.getOne(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody ProductDto productDto) {
        String add = productService.saveOrUpdate(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        String delete = productService.delete(id);
    }
}
