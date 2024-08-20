package com.hyperlogy_ban_hang_2.dto;

import com.hyperlogy_ban_hang_2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDto {
    private Long id;
    private String code;
    private String email;
    private String roleName;
    private String jwt;
}
