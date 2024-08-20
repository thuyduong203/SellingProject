package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.SizeDto;
import com.hyperlogy_ban_hang_2.entity.Size;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    Size mapSizeDtoToSize(SizeDto sizeDto);

    SizeDto mapSizeToSizeDto(Size size);
}
