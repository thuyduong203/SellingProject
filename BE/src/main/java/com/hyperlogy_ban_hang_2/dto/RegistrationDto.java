package com.hyperlogy_ban_hang_2.dto;

import lombok.Builder;

@Builder
public class RegistrationDto {

    private String email;

    private String password;

    private Long roleId;


    public RegistrationDto() {
    }

    public RegistrationDto(String email, String password, Long roleId) {
        super();
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
