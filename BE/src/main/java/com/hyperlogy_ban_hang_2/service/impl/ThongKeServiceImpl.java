package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.repository.BillDetailRepository;
import com.hyperlogy_ban_hang_2.service.ThongKeService;
import com.hyperlogy_ban_hang_2.util.ConvertDate;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private ConvertDate convertDate;

    @Override
    public void exportDoanhThu(HttpServletResponse response, Timestamp thoiGianMin, Timestamp thoiGianMax) {
        try {
            Long tongDoanhThuTheoKhoangNgay = billDetailRepository.tinhDoanhThuTrongKhoangNgay(thoiGianMin, thoiGianMax);

            // Tạo workbook mới
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Doanh_Thu");

            // Tạo dòng tiêu đề
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("From");
            headerRow.createCell(1).setCellValue("To");
            headerRow.createCell(2).setCellValue("Total");

            // Tạo các dòng dữ liệu
            int rowNum = 1;
            Row row = sheet.createRow(rowNum);
//            row.createCell(0).setCellValue(thoiGianMin);
            Timestamp input = thoiGianMin;
            row.createCell(0).setCellValue(convertDate.convertTimestampToDate(input));
            row.createCell(1).setCellValue(thoiGianMax);
            row.createCell(2).setCellValue(tongDoanhThuTheoKhoangNgay);


            // Thiết lập response header
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //=> thiet lap content type la "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            //=> noi dung phan hoi la 1 tep excel
            response.setHeader("Content-Disposition", "attachment; filename=Doanh_Thu.xlsx");
            //Header này cho biết cho trình duyệt rằng phản hồi là một tệp
            // đính kèm ("attachment") và đặt tên cho tệp là "products.xlsx".
            // Điều này khiến trình duyệt hiểu rằng nó nên tải xuống tệp Excel và lưu tệp với tên "products.xlsx".

            // Ghi workbook vào response
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long tinhDoanhThuTrongKhoangNgay(Timestamp thoiGianMin, Timestamp thoiGianMax) {
        return billDetailRepository.tinhDoanhThuTrongKhoangNgay(thoiGianMin, thoiGianMax);
    }
}
