package com.hyperlogy_ban_hang_2.service;

import java.util.List;

public interface GeneralService<TDto> {
    List<TDto> getAll();

    String saveOrUpdate(TDto tDto);

    TDto getOne(Long id);

    String delete(Long id);

}
