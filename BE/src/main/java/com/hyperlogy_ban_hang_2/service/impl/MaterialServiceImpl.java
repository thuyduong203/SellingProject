package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.MaterialDto;
import com.hyperlogy_ban_hang_2.entity.Material;
import com.hyperlogy_ban_hang_2.mapper.MarterialMapper;
import com.hyperlogy_ban_hang_2.repository.MaterialRepository;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements GeneralService<MaterialDto> {
    private final MarterialMapper marterialMapper;
    private final MaterialRepository materialRepository;

    @Override
    public List<MaterialDto> getAll() {
        return materialRepository.findAll().stream().map(marterial -> marterialMapper.mapMarToMarDto(marterial)).toList();
    }

    @Override
    public String saveOrUpdate(MaterialDto materialDto) {
        materialRepository.save(marterialMapper.mapMarterialDtoToMarterial(materialDto));
        return "Thanh cong";
    }

    @Override
    public MaterialDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Material material = materialRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay!"));
            return marterialMapper.mapMarToMarDto(material);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Material material = materialRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay!"));
            materialRepository.delete(material);
        }
        return "Da xoa!";
    }
}
