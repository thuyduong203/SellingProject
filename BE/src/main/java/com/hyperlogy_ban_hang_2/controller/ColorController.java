package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.ColorDto;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.impl.ColorServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/color")
@CrossOrigin(origins = {"*"})
public class ColorController {
    private final GeneralService<ColorDto> colorService;

    @PostMapping("/add")
    public void save(@RequestBody ColorDto colorDto) {
        String add = colorService.saveOrUpdate(colorDto);
    }

    @GetMapping("/get-all")
    public List<ColorDto> getAllColor() {
        return colorService.getAll();
    }

    @PutMapping("/update")
    public String updateColor(@RequestBody ColorDto colorDto) {
        return colorService.saveOrUpdate(colorDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteColor(@PathVariable("id") Long id) {
        String delete = colorService.delete(id);
    }

    @GetMapping("/get-one/{id}")
    public ColorDto getOneColor(@PathVariable("id") Long id) {
        return colorService.getOne(id);
    }
}

