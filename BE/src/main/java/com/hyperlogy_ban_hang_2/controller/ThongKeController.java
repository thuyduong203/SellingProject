package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.service.ThongKeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/thong-ke")
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/doanh-thu-theo-khoang-ngay")
    public Long thongKeDoanhThuTheoKhoangNgay(@RequestParam("thoiGianMin") Timestamp thoiGianMin,
                                              @RequestParam("thoiGianMax") Timestamp thoiGianMax) {
        return thongKeService.tinhDoanhThuTrongKhoangNgay(thoiGianMin, thoiGianMax);
    }

    @GetMapping("/export-doanh-thu-theo-khoang-ngay")
    public void exportDoanhThuTheoKhoangNgay(HttpServletResponse response,
                                             @RequestParam("thoiGianMin") Timestamp thoiGianMin,
                                             @RequestParam("thoiGianMax") Timestamp thoiGianMax) {
        thongKeService.exportDoanhThu(response, thoiGianMin, thoiGianMax);
    }

}
