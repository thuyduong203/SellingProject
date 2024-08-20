package com.hyperlogy_ban_hang_2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillResponse {
    private Long idBill;
    private String code;
    private String createdCode;
    private String customerCode;
    private Timestamp createdTime;
    private int status;
}
