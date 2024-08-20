package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.BillDto;
import com.hyperlogy_ban_hang_2.dto.BillDtoRes;
import com.hyperlogy_ban_hang_2.dto.response.BillResponse;
import com.hyperlogy_ban_hang_2.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                CustomerMapper.class,
                AdminMapper.class
        }
)
public interface BillMapper {
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "customer.id", target = "customerId")
    BillDto mapBillToBillDto(Bill bill);

    @Mapping(source = "createdById", target = "createdBy.id")
    @Mapping(source = "customerId", target = "customer.id")
    Bill mapBillDtoToBill(BillDto billDto);

    BillDtoRes mapBillToBillDtoRes(Bill bill);

    @Mapping(source = "createdBy.id", target = "createdCode")
    @Mapping(source = "customer.id", target = "customerCode")
    BillResponse mapBillToBillResponse(Bill bill);
}
