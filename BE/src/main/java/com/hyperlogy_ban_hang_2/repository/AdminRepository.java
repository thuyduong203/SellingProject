package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.dto.AdminDto;
import com.hyperlogy_ban_hang_2.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByEmail(String email);
}
