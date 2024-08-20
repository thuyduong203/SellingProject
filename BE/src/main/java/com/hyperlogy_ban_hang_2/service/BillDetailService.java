package com.hyperlogy_ban_hang_2.service;

import com.hyperlogy_ban_hang_2.entity.BillDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillDetailService {
    List<BillDetail> getBillDetailByBill(Long idBill);

    Long getTongTienHoaDon(Long idBill);

}
