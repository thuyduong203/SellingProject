package com.hyperlogy_ban_hang_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillDto {

    private Long id;

    private Long createdById;

    private Long customerId;

    private String code;

    private Timestamp createdTime;

    private String receicverName;

    private Timestamp receiverDate;

    private Timestamp paymentTime;

    private String receiverAddress;

    private String phoneNumber;

    private int status;

}
