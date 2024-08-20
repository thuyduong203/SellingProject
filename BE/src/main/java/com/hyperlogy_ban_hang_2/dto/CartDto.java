package com.hyperlogy_ban_hang_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDto {

    private Long id;

    private String code;

    private Long customerId;

}
