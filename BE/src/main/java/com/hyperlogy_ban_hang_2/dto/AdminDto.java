package com.hyperlogy_ban_hang_2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDto {

    private Long id;

    @Length(max = 10)
    @NotNull(message = "code not null")
    private String code;

    @NotNull(message = "name not null")
    private String name;

    @NotNull(message = "address not null")
    private String address;

    private String phoneNumber;

    @Email(message = "khong dung dinh dang")
    private String email;

    private String password;

    private int status;

}
