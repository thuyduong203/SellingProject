package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.ProductDto;
import com.hyperlogy_ban_hang_2.entity.Product;
import com.hyperlogy_ban_hang_2.mapper.ProductMapper;
import com.hyperlogy_ban_hang_2.repository.ProductRepository;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements GeneralService<ProductDto> {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(product -> productMapper.mapProToProDto(product)).toList();
    }

    @Override
    public String saveOrUpdate(ProductDto productDto) {
        productRepository.save(productMapper.mapProDtoToPro(productDto));
        return "Thanh cong";
    }

    @Override
    public ProductDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay!"));
            return productMapper.mapProToProDto(product);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay!"));
            productRepository.delete(product);
        }
        return "Da xoa";
    }
}
