package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.SizeDto;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.impl.SizeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/size")
@CrossOrigin(origins = {"*"})
public class SizeController {
    private final GeneralService<SizeDto> sizeService;

    @GetMapping("/get-all")
    public List<SizeDto> getAll() {
        return sizeService.getAll();
    }

    @PostMapping("/add")
    public void save(@RequestBody SizeDto sizeDto) {
        String add = sizeService.saveOrUpdate(sizeDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        sizeService.delete(id);
    }

}
