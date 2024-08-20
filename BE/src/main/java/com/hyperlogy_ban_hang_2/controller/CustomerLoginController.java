package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.CustomerDto;
import com.hyperlogy_ban_hang_2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
////@RequestMapping("/guest")
//public class CustomerLoginController {
//    private final CustomerService customerService;
//
//    @PostMapping("/guest-login")
//    public CustomerDto customerLogin(@RequestBody CustomerDto customerDto) {
//        CustomerDto customer = customerService.getByEmalAndPass(customerDto.getEmail(), customerDto.getPassword());
//        if (customer != null) {
//            return customer;
//        }
//        return null;
//    }
//}
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerLoginController {
    private final CustomerService customerService;

    @PostMapping("/guest-login")
    public CustomerDto customerLogin(@RequestBody CustomerDto customerDto) {
        CustomerDto customer = customerService.getByEmalAndPass(customerDto.getEmail(), customerDto.getPassword());
        if (customer != null) {
            return customer;
        } else {
            return null;
        }
    }
}