package com.hyperlogy_ban_hang_2.service;

import com.hyperlogy_ban_hang_2.dto.CartDto;
import com.hyperlogy_ban_hang_2.entity.Cart;

public interface CartService {
    CartDto getByCustomerId(String customerrId);
}
