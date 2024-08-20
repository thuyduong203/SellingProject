package com.hyperlogy_ban_hang_2.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDetailDto {

    private Long id;

    private Long cartId;

    private Long productDetailId;

    @Min(value = 1, message = "So luong khong hop le")
    private int quanity;

    private BigDecimal price;

}
