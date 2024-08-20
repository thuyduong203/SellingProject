package com.hyperlogy_ban_hang_2.service;

import com.hyperlogy_ban_hang_2.dto.BillDto;
import com.hyperlogy_ban_hang_2.dto.BillDtoRes;
import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import com.hyperlogy_ban_hang_2.response.BillResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BillService {
    List<BillDtoRes> getAll();

    Page<com.hyperlogy_ban_hang_2.dto.response.BillResponse> getBIllRespone(int pageNo);

    BillDto save(BillDto billDto);

    BillDtoRes getOne(Long id);

    String delete(Long id);

    List<Bill> getBillByCustomer(Long idCustomer);

    List<Bill> getBillByCreatedById(Long id);

    List<Bill> getBillByStatus(int status);

    String xacNhanThanhToan(Long idBill);

    void datHang(List<CartDetail> listCartDetail);

    List<Bill> getOrderByNgayTao();

    List<Bill> getBillOrderByTongTien();

    Bill getBillByCode(String code);

    List<Bill> getBillOrderByTongTienAndCustomer(Long idCustomer);

    String huyDonHang(Long idBill);

    String capNhatTrangThaiHD(Long idBill, int trangThai);

    List<BillResponse> getByTrangThai();

    BigDecimal getTongTien(Long idBill);


}
