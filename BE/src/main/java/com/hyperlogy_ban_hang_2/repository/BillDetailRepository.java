package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    List<BillDetail> getBillDetailByBill(Bill bill);

    @Query("SELECT SUM(b.quanity * b.price) FROM BillDetail b WHERE b.bill.idBill = :idBill")
    Long getTongTienHoaDon(@Param("idBill") Long idBill);

    @Query("SELECT SUM(b.quanity * b.price) " +
            "FROM BillDetail b " +
            "WHERE b.bill.createdTime >= :thoiGianMin AND b.bill.createdTime <= :thoiGianMax " +
            "AND b.bill.status = 5")
    Long tinhDoanhThuTrongKhoangNgay(@Param("thoiGianMin") Timestamp thoiGianMin, @Param("thoiGianMax") Timestamp thoiGianMax);
}
