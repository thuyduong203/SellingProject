package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.BillDetailDto;
import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.entity.BillDetail;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                BillMapper.class,
                ProductDetailMapper.class,
                CartDetailMapper.class
        }
)
public interface BillDetailMapper {
    @Mapping(source = "billId", target = "bill.idBill")
    @Mapping(source = "productDetailId", target = "productDetail.id")
    BillDetail mapBillDetailDtoToBillDetail(BillDetailDto billDetailDto);

    @Mapping(source = "bill.idBill", target = "billId")
    @Mapping(source = "productDetail.id", target = "productDetailId")
    BillDetailDto mapBillDetailToBillDetailDto(BillDetail billDetail);


    BillDetail mapCartDetailToBillDetail(CartDetail cartDetail);
}
