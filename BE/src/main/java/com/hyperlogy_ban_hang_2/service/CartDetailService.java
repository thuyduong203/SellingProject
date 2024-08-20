package com.hyperlogy_ban_hang_2.service;

import com.hyperlogy_ban_hang_2.entity.CartDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailService {
    CartDetail getOneEntity(Long id);

    List<CartDetail> getCartDetailByCartId(Long idCart);

    List<CartDetail> getCartDetailByIdCustomer(Long idCustomer);

}
