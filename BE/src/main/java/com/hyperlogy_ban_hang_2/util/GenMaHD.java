package com.hyperlogy_ban_hang_2.util;

import com.hyperlogy_ban_hang_2.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GenMaHD {
    private final BillRepository billRepository;

    @Bean
    public String autoGen() {
        return "HD" + billRepository.countBillByCode();
    }
}
