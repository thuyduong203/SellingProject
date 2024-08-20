package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.CartDto;
import com.hyperlogy_ban_hang_2.entity.Cart;
import com.hyperlogy_ban_hang_2.service.CartService;
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
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class CartController {
    private final GeneralService<CartDto> cartServiceGeneral;
    private final CartService cartService;
    private List<CartDto> listCart = new ArrayList<>();

    @GetMapping("/get-all")
    public List<CartDto> getAll() {
        return cartServiceGeneral.getAll();
    }

    @PostMapping("/add")
    public String save(@RequestBody CartDto cartDto) {
        return cartServiceGeneral.saveOrUpdate(cartDto);
    }

    @PutMapping("/update")
    public String update(@RequestBody CartDto cartDto) {
        return cartServiceGeneral.saveOrUpdate(cartDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return cartServiceGeneral.delete(id);
    }

    @GetMapping("/get-by-customerId/{idCustomer}")
    public CartDto getByCustomerId(@PathVariable("idCustomer") String idCustomer) {
        return cartService.getByCustomerId(idCustomer);
    }
}
