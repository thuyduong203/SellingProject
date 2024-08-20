package com.hyperlogy_ban_hang_2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDetailDto {
    private Long id;

    private Long productId;

    private Long categoryId;

    private Long sizeId;

    private Long materialId;

    private Long colorId;

    private String style;

    private int quanity;

    private BigDecimal price;

    private String moTa;

    private int trangThai;

}
