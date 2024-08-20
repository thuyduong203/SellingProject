package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.MaterialDto;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/material")
@CrossOrigin(origins = {"*"})
public class MaterialController {
    private final GeneralService<MaterialDto> materialService;

    @PostMapping("/add")
    public void save(@RequestBody MaterialDto materialDto) {
        String add = materialService.saveOrUpdate(materialDto);
    }

    @GetMapping("/get-all")
    public List<MaterialDto> getAllMaterial() {
        return materialService.getAll();
    }

    @PutMapping("/update")
    public String updateMaterial(@RequestBody MaterialDto materialDto) {
        return materialService.saveOrUpdate(materialDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMaterial(@PathVariable("id") Long id) {
        String delete = materialService.delete(id);
    }

    @GetMapping("/get-one/{id}")
    public MaterialDto getOneMaterial(@PathVariable("id") Long id) {
        return materialService.getOne(id);
    }
}
