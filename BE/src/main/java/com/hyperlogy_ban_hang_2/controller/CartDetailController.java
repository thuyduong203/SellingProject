package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.CartDetailDto;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import com.hyperlogy_ban_hang_2.repository.CartDetailRepository;
import com.hyperlogy_ban_hang_2.service.CartDetailService;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import jakarta.validation.Valid;
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

import java.util.List;

@RestController
@RequestMapping("/cart-detail")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class CartDetailController {
    private final GeneralService<CartDetailDto> cartDetailGeneralService;
    private final CartDetailService cartDetailService;
    private final CartDetailRepository cartDetailRepository;

    @GetMapping("/get-all")
    public List<CartDetailDto> getAll() {
        return cartDetailGeneralService.getAll();
    }

    @PostMapping("/add")
    public void save(@Valid @RequestBody CartDetailDto cartDetailDto) {
        String add = cartDetailGeneralService.saveOrUpdate(cartDetailDto);
    }

    @PutMapping("/update")
    public String update(@Valid @RequestBody CartDetailDto cartDetailDto) {
        return cartDetailGeneralService.saveOrUpdate(cartDetailDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return cartDetailGeneralService.delete(id);
    }

    @GetMapping("/get-one/{id}")
    public CartDetail getOne(@PathVariable("id") Long id) {
        return cartDetailService.getOneEntity(id);
    }

    @GetMapping("/get-by-cart/{idCart}")
    public List<CartDetail> getCartDetailByCart(@PathVariable("idCart") Long idCart) {
        return cartDetailService.getCartDetailByCartId(idCart);
    }

    @GetMapping("/get-cartDetail")
    public List<CartDetail> get() {
        return cartDetailRepository.findAll();
    }

    @GetMapping("/get-cart-detail-by-idCustomer/{idCustomer}")
    public List<CartDetail> getCartDetailByIdCustomer(@PathVariable("idCustomer") Long idCustomer) {
        return cartDetailService.getCartDetailByIdCustomer(idCustomer);
    }
}
