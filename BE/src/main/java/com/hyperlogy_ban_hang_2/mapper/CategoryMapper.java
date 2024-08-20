package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.CategoryDto;
import com.hyperlogy_ban_hang_2.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category mapCateDtoToCate(CategoryDto categoryDto);

    CategoryDto mapCateToCateDto(Category category);
}
