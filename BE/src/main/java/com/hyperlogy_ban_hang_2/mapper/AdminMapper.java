package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.AdminDto;
import com.hyperlogy_ban_hang_2.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin mapAdminDtoToAdmin(AdminDto adminDto);

    AdminDto mapAdminToAdminDto(Admin admin);
}
