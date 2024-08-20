package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.BillDetailDto;
import com.hyperlogy_ban_hang_2.entity.BillDetail;
import com.hyperlogy_ban_hang_2.service.BillDetailService;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/bill-detail")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class BillDetailController {
    private final GeneralService<BillDetailDto> billDetailDtoGeneralService;
    private final BillDetailService billDetailService;
    private List<BillDetailDto> listBillDetail = new ArrayList<>();

    @GetMapping("/get-all")
    public List<BillDetailDto> getAll() {
        return listBillDetail = billDetailDtoGeneralService.getAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody BillDetailDto billDetailDto) {
        return billDetailDtoGeneralService.saveOrUpdate(billDetailDto);
    }

    @PutMapping("/update")
    public String update(@RequestBody BillDetailDto billDetailDto) {
        return billDetailDtoGeneralService.saveOrUpdate(billDetailDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return billDetailDtoGeneralService.delete(id);
    }

    @GetMapping("/get-by-bill/{idBill}")
    public List<BillDetail> getBillDetailByBill(@PathVariable("idBill") Long idBill) {
        return billDetailService.getBillDetailByBill(idBill);
    }

    //get tong tien hoa don 1
    @GetMapping("/get-tong-tien-HD/{idBill}")
    public Long getTongTienHD(@PathVariable("idBill") Long idBill) {
        return billDetailService.getTongTienHoaDon(idBill);
    }
}
