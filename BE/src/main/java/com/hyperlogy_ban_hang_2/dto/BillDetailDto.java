package com.hyperlogy_ban_hang_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillDetailDto {

    private Long id;

    private Long billId;

    private Long productDetailId;

    private int quanity;

    private BigDecimal price;

}
