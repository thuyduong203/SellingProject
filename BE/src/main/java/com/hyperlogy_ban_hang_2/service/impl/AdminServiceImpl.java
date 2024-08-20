package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.AdminDto;
import com.hyperlogy_ban_hang_2.entity.Admin;
import com.hyperlogy_ban_hang_2.mapper.AdminMapper;
import com.hyperlogy_ban_hang_2.repository.AdminRepository;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements GeneralService<AdminDto> {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public List<AdminDto> getAll() {
        return adminRepository.findAll().stream().map(admin -> adminMapper.mapAdminToAdminDto(admin)).toList();
    }

    @Override
    public String saveOrUpdate(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setAddress(adminDto.getAddress());
        admin.setPassword(adminDto.getPassword());
        admin.setCode(adminDto.getCode());
        admin.setEmail(adminDto.getEmail());
        admin.setPhoneNumber(adminDto.getPhoneNumber());
        admin.setStatus(adminDto.getStatus());
//        admin.setRole("ADMIN");
        adminMapper.mapAdminToAdminDto(adminRepository.save(admin));
        return "Thanh cong";
    }

    @Override
    public AdminDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            return adminMapper.mapAdminToAdminDto(admin);
        }
    }

    @Override
    public String delete(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
        adminRepository.delete(admin);
        return "Da xoa!";
    }
}
