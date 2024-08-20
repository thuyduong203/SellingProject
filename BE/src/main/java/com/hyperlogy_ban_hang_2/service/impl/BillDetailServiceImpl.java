package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.BillDetailDto;
import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.entity.BillDetail;
import com.hyperlogy_ban_hang_2.entity.ProductDetail;
import com.hyperlogy_ban_hang_2.mapper.BillDetailMapper;
import com.hyperlogy_ban_hang_2.repository.BillDetailRepository;
import com.hyperlogy_ban_hang_2.repository.BillRepository;
import com.hyperlogy_ban_hang_2.repository.ProductDetailRepository;
import com.hyperlogy_ban_hang_2.service.BillDetailService;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements GeneralService<BillDetailDto>, BillDetailService {
    private final BillRepository billRepository;
    private final ProductDetailRepository productDetailRepository;
    private final BillDetailRepository billDetailRepository;
    private final BillDetailMapper billDetailMapper;

    private final ProductDetailService productDetailService;

    @Override
    public List<BillDetailDto> getAll() {
        return billDetailRepository.findAll().stream().map(billDetail -> billDetailMapper.mapBillDetailToBillDetailDto(billDetail)).toList();
    }

    @Override
    public String saveOrUpdate(BillDetailDto billDetailDto) {
        Bill bill = billRepository.findById(billDetailDto.getBillId()).orElse(null);
        ProductDetail productDetail = productDetailRepository.findById(billDetailDto.getProductDetailId()).orElse(null);
        BillDetail billDetail = billDetailMapper.mapBillDetailDtoToBillDetail(billDetailDto);
        billDetail.setBill(bill);
        billDetail.setProductDetail(productDetail);
        if (productDetailService.checkSLTon(productDetail, billDetailDto.getQuanity())) {
            productDetail.setQuanity(productDetail.getQuanity() - billDetailDto.getQuanity());
            billDetailRepository.save(billDetail);
            return "Thanh cong";
        } else {
            return "So luong ton khong du. \n Chi co san " + productDetail.getQuanity() + " san pham";
        }
    }

    @Override
    public BillDetailDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            BillDetail billDetail = billDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thayy"));
            return billDetailMapper.mapBillDetailToBillDetailDto(billDetail);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            BillDetail billDetail = billDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thayy"));
            billDetailRepository.delete(billDetail);
        }
        return "Da xoa";
    }

    @Override
    public List<BillDetail> getBillDetailByBill(Long idBill) {
        Bill bill = billRepository.findById(idBill).orElse(null);
        return billDetailRepository.getBillDetailByBill(bill);
    }

    @Override
    public Long getTongTienHoaDon(Long idBill) {
        return billDetailRepository.getTongTienHoaDon(idBill);
    }


}
