package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.MaterialDto;
import com.hyperlogy_ban_hang_2.entity.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarterialMapper {
    Material mapMarterialDtoToMarterial(MaterialDto materialDto);

    MaterialDto mapMarToMarDto(Material material);
}
