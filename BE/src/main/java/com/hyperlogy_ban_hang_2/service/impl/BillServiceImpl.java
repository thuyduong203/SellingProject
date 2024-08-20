package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.BillDto;
import com.hyperlogy_ban_hang_2.dto.BillDtoRes;
import com.hyperlogy_ban_hang_2.entity.Admin;
import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.entity.BillDetail;
import com.hyperlogy_ban_hang_2.entity.Cart;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import com.hyperlogy_ban_hang_2.entity.Customer;
import com.hyperlogy_ban_hang_2.mapper.BillDetailMapper;
import com.hyperlogy_ban_hang_2.mapper.BillMapper;
import com.hyperlogy_ban_hang_2.repository.AdminRepository;
import com.hyperlogy_ban_hang_2.repository.BillDetailRepository;
import com.hyperlogy_ban_hang_2.repository.BillRepository;
import com.hyperlogy_ban_hang_2.repository.CartDetailRepository;
import com.hyperlogy_ban_hang_2.repository.CartRepository;
import com.hyperlogy_ban_hang_2.repository.CustomerRepository;
import com.hyperlogy_ban_hang_2.response.BillResponse;
import com.hyperlogy_ban_hang_2.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final BillDetailMapper billDetailMapper;
    private final BillDetailRepository billDetailRepository;

    @Override
    public List<BillDtoRes> getAll() {
        return billRepository.findAll().stream().map(bill -> billMapper.mapBillToBillDtoRes(bill)).toList();
    }

    @Override
    public Page<com.hyperlogy_ban_hang_2.dto.response.BillResponse> getBIllRespone(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 2);
        return billRepository.findAll(pageable).map(bill -> billMapper.mapBillToBillResponse(bill));
    }

    @Override
    public BillDto save(BillDto billDto) {
        Customer customer = customerRepository.findById(billDto.getCustomerId()).orElse(null);
        Admin createdBy = adminRepository.findById(billDto.getCreatedById()).orElse(null);
        billDto.setCreatedTime(new Timestamp(new Date().getTime()));
        billDto.setReceiverDate(new Timestamp(new Date().getTime()));
        Bill bill = billMapper.mapBillDtoToBill(billDto);
        bill.setCreatedBy(createdBy);
        bill.setCustomer(customer);
        Long size = Long.valueOf(billRepository.countBillByCode()) + 1;
        bill.setCode("HD" + size);
        //dang chuan bi hang, chua TT: stt = 1
        bill.setStatus(1);
        billRepository.save(bill);
        return billDto;
    }

    @Override
    public BillDtoRes getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Bill bill = billRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay bill co id: " + id));
            return billMapper.mapBillToBillDtoRes(bill);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Bill bill = billRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay bill co id: " + id));
            billRepository.delete(bill);
        }
        return "Da xoa";
    }

    @Override
    public List<Bill> getBillByCustomer(Long idCustomer) {
        return billRepository.getBillByCustomer_Id(idCustomer);
    }

    @Override
    public List<Bill> getBillByCreatedById(Long id) {
        return billRepository.getBillByCreatedBy_Id(id);
    }

    @Override
    public List<Bill> getBillByStatus(int status) {
        return billRepository.getBillByStatus(status);
    }

    @Override
    public String xacNhanThanhToan(Long idBill) {
        Bill bill = billRepository.findById(idBill).orElse(null);
        //thnah toan trang thai = 4
        bill.setStatus(4);
        bill.setPaymentTime(new Timestamp(new Date().getTime()));
        billRepository.save(bill);
        return "Da thanh toan";
    }

    @Override
    public void datHang(List<CartDetail> listCartDetail) {
        //customer chon sp trong gio hang => 1 hoac 1 list cartDetail
        // khi bam dat hang=> chuyen tao 1 hoa don trang thai la 0: cho xac nhan gom thong tin cua KH
        // va sp la list KH da chon
        Cart cart = cartRepository.findById(listCartDetail.get(0).getCart().getId()).get();
        Long size = Long.valueOf(billRepository.countBillByCode());
        String maHD = "HD" + size;
        Bill newBill = Bill.builder()
                .createdTime(new Timestamp(new Date().getTime()))
                .code(maHD)
                .receicverName(cart.getCustomer().getName())
                .receiverAddress(cart.getCustomer().getAddress())
                .phoneNumber(cart.getCustomer().getPhoneNumber())
                .customer(cart.getCustomer())
                .status(0)
                .build();
        billRepository.save(newBill);
        //get one bill vua them:
        Bill bill = billRepository.getBillByCode(maHD);
        //them billDetail:
        for (CartDetail cartDetail : listCartDetail
        ) {
            BillDetail billDetail = billDetailMapper.mapCartDetailToBillDetail(cartDetail);
            billDetail.setBill(bill);
            billDetailRepository.save(billDetail);
        }
    }

    @Override
    public List<Bill> getOrderByNgayTao() {
        return billRepository.getOrderByNgayTao();
    }

    @Override
    public List<Bill> getBillOrderByTongTien() {
        return billRepository.getBillOrderByTongTien();
    }

    @Override
    public Bill getBillByCode(String code) {
        return billRepository.getBillByCode(code);
    }

    @Override
    public List<Bill> getBillOrderByTongTienAndCustomer(Long idCustomer) {
        return billRepository.getBillOrderByTongTienAndCustomer(idCustomer);
    }

    @Override
    public String huyDonHang(Long idBill) {
        Bill bill = billRepository.findById(idBill).orElseThrow(() -> new RuntimeException("khong tim thay"));
        //quy uoc trang thai == 0=> cho xac nhan, == 4: huy
        if (bill.getStatus() == 0) {
            bill.setStatus(4);
            billRepository.save(bill);
        } else {
            return "Hoa don da xac nhan khong the huy";
        }
        return "Da huy hoa don";
    }

    @Override
    public String capNhatTrangThaiHD(Long idBill, int trangThai) {
        //nnhan vien cap nhat trang thai khi trang thai hoa don khac huy:
        Bill bill = billRepository.findById(idBill).orElseThrow(() -> new RuntimeException("khong tim thay"));
        if (bill.getStatus() != 4) {
            bill.setStatus(trangThai);
            billRepository.save(bill);
        } else {
            return "Hoa don da huy"; // FE vo hieu hoa btn
        }
        return "Da cap nhat trang thai";
    }

    @Override
    public List<BillResponse> getByTrangThai() {
        return billRepository.search2();
    }

    @Override
    public BigDecimal getTongTien(Long idBill) {
        return billRepository.getTongTien(idBill);
    }

}
