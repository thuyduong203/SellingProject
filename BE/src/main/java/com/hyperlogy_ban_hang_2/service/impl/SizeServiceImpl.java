package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.SizeDto;
import com.hyperlogy_ban_hang_2.entity.Admin;
import com.hyperlogy_ban_hang_2.entity.Size;
import com.hyperlogy_ban_hang_2.mapper.SizeMapper;
import com.hyperlogy_ban_hang_2.repository.SizeRepository;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements GeneralService<SizeDto> {
    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    @Override
    public List<SizeDto> getAll() {
        return sizeRepository.findAll().stream().map(size -> sizeMapper.mapSizeToSizeDto(size)).toList();
    }

    @Override
    public String saveOrUpdate(SizeDto sizeDto) {
        sizeRepository.save(sizeMapper.mapSizeDtoToSize(sizeDto));
        return "Thanh cong";
    }

    @Override
    public SizeDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Size size = sizeRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thayyyy"));
            return sizeMapper.mapSizeToSizeDto(size);
        }
    }

    @Override
    public String delete(Long id) {
        Size size = sizeRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thayyyy"));
        sizeRepository.delete(size);
        return "Da xoa";
    }
}
