package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.CustomerDto;
import com.hyperlogy_ban_hang_2.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer mapCustomerDtoToCustomer(CustomerDto customerDto);

    CustomerDto mapCusToCusDto(Customer customer);
}
