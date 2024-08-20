package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.response.BillResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> getBillByCustomer_Id(Long id);

    List<Bill> getBillByCreatedBy_Id(Long id);

    List<Bill> getBillByStatus(int status);

    Bill getBillByCode(String maHD);

    @Query("SELECT COUNT(b.idBill) FROM Bill b")
    int countBillByCode();

    @Query("SELECT c FROM Bill c ORDER BY c.createdTime")
    List<Bill> getOrderByNgayTao();

    @Query("SELECT b.bill FROM BillDetail b " +
            "GROUP BY b.bill.idBill\n" +
            "ORDER BY SUM(b.price * b.quanity) DESC")
    List<Bill> getBillOrderByTongTien();

    @Query("SELECT b.bill FROM BillDetail b WHERE b.bill.customer.id = :idCustomer " +
            "GROUP BY b.bill.idBill\n" +
            "ORDER BY SUM(b.price * b.quanity) DESC")
    List<Bill> getBillOrderByTongTienAndCustomer(@Param("idCustomer") Long idCustomer);

    @Query("SELECT SUM(b.quanity * b.price) FROM BillDetail b WHERE b.bill.idBill = :idBill")
    BigDecimal getTongTien(@Param("idBill") Long idBill);

    //    @Query("SELECT NEW com.hyperlogy_ban_hang_2.response.BillResponse" +
//            "(b.idBill, b.createdTime, b.paymentTime, b.createdBy.id, b.createdBy.name," +
//            "b.customer.id, b.customer.name, b.code, SUM(bd.price * bd.quanity), b.status) " +
//            " FROM Bill b INNER JOIN BillDetail bd WHERE b.status = :status")
//    @Query("SELECT NEW com.hyperlogy_ban_hang_2.response.BillResponse" +
//            "(b.bill.idBill, b.bill.createdTime, b.bill.paymentTime, b.bill.createdBy.id, b.bill.createdBy.name," +
//            "b.bill.customer.id, b.bill.customer.name, b.bill.code, " +
//            "CAST(SUM(b.price * b.quanity) AS double), b.bill.status) " +
//            "FROM BillDetail b WHERE b.bill.status = :trangThai GROUP BY b.bill")
//    List<BillResponse> getByTrangThai(@Param("trangThai") int trangThai);
//        @Query("SELECT NEW com.example.xuong.customModel.ProductCustom" +
//            "(p.id, p.name, p.color, p.sellPrice, p.originPrice, p.quantity,  p.subCategory, p.status, b) " +
//            "FROM  Product p INNER JOIN ProductBrand pb ON p.id = pb.product.id\n" +
//            "INNER JOIN Brand b ON b.id = pb.brand.id ORDER BY p.id")
//    List<ProductCustom> getAllCustom();

    @Query(value = "SELECT b.id, b.created_by_id, b.customer_id, b.created_time, b.payment_time, SUM(bd.price * bd.quanity), b.status\n" +
            "FROM bill b inner join bill_detail bd ON b.id = bd.bill_id\n WHERE b.status  = 4" +
            " GROUP BY b.id, b.created_by_id, b.customer_id, b.created_time, b.payment_time", nativeQuery = true)
    List<BillResponse> search2();

}
