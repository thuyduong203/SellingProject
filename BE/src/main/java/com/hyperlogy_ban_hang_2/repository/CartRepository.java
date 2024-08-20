package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByCustomer_Id(String customerId);
}
