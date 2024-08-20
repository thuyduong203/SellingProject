package com.hyperlogy_ban_hang_2.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface ThongKeService {
    void exportDoanhThu(HttpServletResponse response, Timestamp thoiGianMin, Timestamp thoiGianMax);

    Long tinhDoanhThuTrongKhoangNgay(Timestamp thoiGianMin, Timestamp thoiGianMax);

}
