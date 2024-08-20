package com.hyperlogy_ban_hang_2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerRegisterReq {

    private Long id;

    private String code;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String password;

    private Long roleId;

}
