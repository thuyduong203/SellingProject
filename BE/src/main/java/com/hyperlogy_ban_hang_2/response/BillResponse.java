package com.hyperlogy_ban_hang_2.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillResponse {

    private Long id;

    private Long createdById;

    private Long customerId;

    private Timestamp createTime;

    private Timestamp paymentTime;

    private BigDecimal tongTien;

    private int trangThai;

}
