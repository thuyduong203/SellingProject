package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.ColorDto;
import com.hyperlogy_ban_hang_2.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    Color mapColorDtoToColor(ColorDto colorDto);

    ColorDto mapColorToColorDto(Color color);
}
