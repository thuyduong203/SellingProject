package com.hyperlogy_ban_hang_2.service;

import com.hyperlogy_ban_hang_2.dto.CustomerDto;
import com.hyperlogy_ban_hang_2.dto.request.CustomerRegisterReq;
import com.hyperlogy_ban_hang_2.entity.CartDetail;

import java.util.List;

public interface CustomerService {
    String datHang(List<CartDetail> listCartDetail);

    CustomerDto getByEmalAndPass(String email, String pass);

    String dangKiTaiKhoanUser(CustomerRegisterReq customerRegisterReq);
}
