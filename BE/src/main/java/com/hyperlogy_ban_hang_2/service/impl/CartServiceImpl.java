package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.CartDto;
import com.hyperlogy_ban_hang_2.entity.Cart;
import com.hyperlogy_ban_hang_2.entity.Customer;
import com.hyperlogy_ban_hang_2.mapper.CartMapper;
import com.hyperlogy_ban_hang_2.repository.CartRepository;
import com.hyperlogy_ban_hang_2.repository.CustomerRepository;
import com.hyperlogy_ban_hang_2.service.CartService;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements GeneralService<CartDto>, CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CustomerRepository customerRepository;

    @Override
    public List<CartDto> getAll() {
        return cartRepository.findAll().stream().map(cart -> cartMapper.mapCartToCartDto(cart)).toList();
    }

    @Override
    public String saveOrUpdate(CartDto cartDto) {
        Customer customer = customerRepository.findById(cartDto.getCustomerId()).orElse(null);
        Cart cart = cartMapper.mapCartDtoToCart(cartDto);
        cart.setCustomer(customer);
        cartRepository.save(cart);
        return "Thanh cong";
    }

    @Override
    public CartDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id cart is null");
        } else {
            Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay cart co id: " + id));
            return cartMapper.mapCartToCartDto(cart);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id cart is null");
        } else {
            Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay cart co id: " + id));
            cartRepository.delete(cart);
        }
        return "Da xoa";
    }

    @Override
    public CartDto getByCustomerId(String customerrId) {
        Cart cart = cartRepository.findCartByCustomer_Id(customerrId);
        if (cart != null) {
            return cartMapper.mapCartToCartDto(cart);
        } else {
            return null;
        }
    }
}
