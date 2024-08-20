package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.ColorDto;
import com.hyperlogy_ban_hang_2.entity.Color;
import com.hyperlogy_ban_hang_2.mapper.ColorMapper;
import com.hyperlogy_ban_hang_2.repository.ColorRepository;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements GeneralService<ColorDto> {
    private final ColorMapper colorMapper;
    private final ColorRepository colorRepository;

    @Override
    public List<ColorDto> getAll() {
        return colorRepository.findAll().stream().map(color -> colorMapper.mapColorToColorDto(color)).toList();
    }

    @Override
    public String saveOrUpdate(ColorDto colorDto) {
        colorRepository.save(colorMapper.mapColorDtoToColor(colorDto));
        return "Thanh cong";
    }

    @Override
    public ColorDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            return colorMapper.mapColorToColorDto(color);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            colorRepository.delete(color);
        }
        return "Da xoa";
    }
}
