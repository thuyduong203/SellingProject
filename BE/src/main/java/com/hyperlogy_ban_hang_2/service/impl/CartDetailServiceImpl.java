package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.CartDetailDto;
import com.hyperlogy_ban_hang_2.entity.Cart;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import com.hyperlogy_ban_hang_2.entity.ProductDetail;
import com.hyperlogy_ban_hang_2.mapper.CartDetailMapper;
import com.hyperlogy_ban_hang_2.repository.CartDetailRepository;
import com.hyperlogy_ban_hang_2.repository.CartRepository;
import com.hyperlogy_ban_hang_2.repository.ProductDetailRepository;
import com.hyperlogy_ban_hang_2.service.CartDetailService;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements GeneralService<CartDetailDto>, CartDetailService {
    private final CartDetailMapper cartDetailMapper;
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailService productDetailService;

    @Override
    public List<CartDetailDto> getAll() {
        return cartDetailRepository.findAll().stream().map(cartDetail -> cartDetailMapper.mapCartDetailToCartDetailDto(cartDetail)).toList();
    }

    @Override
    public String saveOrUpdate(CartDetailDto cartDetailDto) {
        //them sp vao gio hang:
        Cart cart = cartRepository.findById(cartDetailDto.getCartId()).orElse(null);
        ProductDetail productDetail = productDetailRepository.findById(cartDetailDto.getProductDetailId()).orElse(null);
        CartDetail cartDetail = cartDetailMapper.mapCartDetailDtoToCartDetail(cartDetailDto);
        cartDetail.setCart(cart);
        cartDetail.setProductDetail(productDetail);
        if (productDetailService.checkSLTon(productDetail, cartDetailDto.getQuanity())) {
            cartDetailRepository.save(cartDetail);
            productDetail.setQuanity(productDetail.getQuanity() - cartDetailDto.getQuanity());
            return "Thanh cong";
        } else {
            return "So luong ton khong du. \n Chi co san " + productDetail.getQuanity() + " san pham";
        }

    }

    @Override
    public CartDetailDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            CartDetail cartDetail = cartDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thayy"));
            return cartDetailMapper.mapCartDetailToCartDetailDto(cartDetail);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            CartDetail cartDetail = cartDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thayy"));
            cartDetailRepository.delete(cartDetail);
        }
        return "Da xoa";
    }

    @Override
    public CartDetail getOneEntity(Long id) {
        return cartDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
    }

    @Override
    public List<CartDetail> getCartDetailByCartId(Long idCart) {
        return cartDetailRepository.getCartDetailByCart_Id(idCart);
    }

    @Override
    public List<CartDetail> getCartDetailByIdCustomer(Long idCustomer) {
        return cartDetailRepository.getCartDetailByIdCustomer(idCustomer);
    }
}
