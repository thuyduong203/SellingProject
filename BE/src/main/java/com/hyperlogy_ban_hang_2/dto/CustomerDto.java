package com.hyperlogy_ban_hang_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {

    private Long id;

    private String code;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String password;


}
