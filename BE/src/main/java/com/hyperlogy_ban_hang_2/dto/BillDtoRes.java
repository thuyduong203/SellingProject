package com.hyperlogy_ban_hang_2.dto;

import com.hyperlogy_ban_hang_2.entity.Admin;
import com.hyperlogy_ban_hang_2.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillDtoRes {

    private Long id;

    private Admin createdBy;

    private Customer customer;

    private String code;

    private Timestamp createdTime;

    private String receicverName;

    private Timestamp receiverDate;

    private String receiverAddress;

    private String phoneNumber;

    private int status;

}
