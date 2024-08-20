package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    List<CartDetail> getCartDetailByCart_Id(Long idCart);

    @Query("SELECT c FROM CartDetail c WHERE c.cart.customer.id = :idCustomer")
    List<CartDetail> getCartDetailByIdCustomer(@Param("idCustomer") Long idCustomer);
}
