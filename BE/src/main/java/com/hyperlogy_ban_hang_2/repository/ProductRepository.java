package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
