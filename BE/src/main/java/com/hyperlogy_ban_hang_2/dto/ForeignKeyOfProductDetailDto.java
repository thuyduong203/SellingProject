package com.hyperlogy_ban_hang_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ForeignKeyOfProductDetailDto {
    private String categoryId;
    private String productId;
    private String colorId;
    private String sizeId;
    private String materialId;

}
