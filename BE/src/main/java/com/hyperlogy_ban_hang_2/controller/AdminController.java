package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.AdminDto;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/admin/quan-tri")
@CrossOrigin(origins = {"*"})
public class AdminController {
    private final GeneralService<AdminDto> adminService;

    @GetMapping("/get-all")
    public List<AdminDto> getAll() {
        return adminService.getAll();
    }

    @GetMapping("/get-one/{id}")
    public AdminDto getOne(@PathVariable("id") Long id) {
        return adminService.getOne(id);
    }


    @PostMapping("/add")
    public String add(@Valid @RequestBody AdminDto adminDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return String.valueOf(bindingResult.getAllErrors());
        }
        return adminService.saveOrUpdate(adminDto);
    }

    @PutMapping("/update")
    public String Update(@RequestBody AdminDto adminDto) {
        return adminService.saveOrUpdate(adminDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return adminService.delete(id);
    }
}
