package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.BillDto;
import com.hyperlogy_ban_hang_2.dto.BillDtoRes;
import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.repository.BillRepository;
import com.hyperlogy_ban_hang_2.response.BillResponse;
import com.hyperlogy_ban_hang_2.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = {"*"})
public class BillController {
    private final BillService billService;
    private final BillRepository billRepository;

    @GetMapping("/bill-get-all")
    public List<BillDtoRes> getAllBill() {
        return billService.getAll();
    }

    @PostMapping("/bill-add")
    public String testAddBill(@RequestBody BillDto billDto) {
        billService.save(billDto);
        return "Da them";
    }

    @GetMapping("/bill-get-bill-by-customer/{idCustomer}")
    public List<Bill> getBillByCustomer(@PathVariable("idCustomer") Long idCustomer) {
        return billService.getBillByCustomer(idCustomer);
    }

    @GetMapping("/bill-get-bill-by-created/{idCreated}")
    public List<Bill> getBillByCreated(@PathVariable("idCreated") Long idCreated) {
        return billService.getBillByCreatedById(idCreated);
    }

    @GetMapping("/bill-get-bill-by-status/{stt}")
    public List<Bill> getBillByStatus(@PathVariable("stt") int stt) {
        return billService.getBillByStatus(stt);
    }

    @PutMapping("/bill-thanh-toan/{idBill}")
    public String thanhToan(@PathVariable("idBill") Long idBill) {
        return billService.xacNhanThanhToan(idBill);
    }

    @GetMapping("/bill-get-orderBy-createdTime")
    public List<Bill> getOrderByCreatedTime() {
        return billService.getOrderByNgayTao();
    }

    @GetMapping("/bill-get-orderBy-tong-tien")
    public List<Bill> getBillOrderByTongTien() {
        return billService.getBillOrderByTongTien();
    }

    @GetMapping("/bill-get-orderBy-tong-tien-customer/{idCustomer}")
    public List<Bill> getBillOrderByTongTienAndCustomer(@PathVariable("idCustomer") Long idCustomer) {
        return billService.getBillOrderByTongTienAndCustomer(idCustomer);
    }

    @GetMapping("/bill-get-by-code")
    public Bill getBillByCode(@RequestParam("code") String code) {
        return billService.getBillByCode(code);
    }

    @PutMapping("/bill-guest/huy-don-hang/{idBill}")
    public String huyDonHang(@PathVariable("idBill") Long idBill) {
        return billService.huyDonHang(idBill);
    }

    @PutMapping("/bill-cap-nhat-trang-thai/{idBIll}")
    public String capNhatTrangThaiHD(@PathVariable("idBll") Long idBill, @RequestParam("trangThai") int trangThai) {
        return billService.capNhatTrangThaiHD(idBill, trangThai);
    }

    //    @GetMapping("/get-hoa-don-theo-tt/{trangThai}")
//    public List<Bill> getByTT(@PathVariable("trangThai") int trangThai) {
//        return billService.getByTrangThai(trangThai);
//    }
    @GetMapping("/bill-get-hoa-don-theo-tt")
    public List<BillResponse> getByTT() {
        return billService.getByTrangThai();
    }

    @GetMapping("/bill-get-entity")
    public List<Bill> getEntity() {
        return billRepository.findAll();
    }

    @GetMapping("/bill/get-response")
    public Page<com.hyperlogy_ban_hang_2.dto.response.BillResponse> getBillResponse(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo
    ) {
        return billService.getBIllRespone(pageNo);
    }

    @GetMapping("/bill-get-tong-tien/{idBill}")
    public BigDecimal getTongTien(@PathVariable("idBill") Long idBill) {
        return billRepository.getTongTien(idBill);
    }
}
