package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.CustomerDto;
import com.hyperlogy_ban_hang_2.dto.request.CustomerRegisterReq;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import com.hyperlogy_ban_hang_2.service.BillService;
import com.hyperlogy_ban_hang_2.service.CustomerService;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.auth.AuthenticationService;
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
@RequiredArgsConstructor
@RequestMapping("/customer")
@CrossOrigin(origins = {"*"})
public class CustomerController {
    private final GeneralService<CustomerDto> customerService;
    private final BillService billService;
    //goi authen service => register => tra ve email => getONE => update thong tin
    private final AuthenticationService authenticationService;


    @GetMapping("/get-all")
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAll();
    }

    @PostMapping("/add")
    public String saveCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.saveOrUpdate(customerDto);
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.saveOrUpdate(customerDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBill(@PathVariable("id") Long id) {
        return customerService.delete(id);
    }

    @GetMapping("/get-one/{id}")
    public CustomerDto getOneCustomer(@PathVariable("id") Long id) {
        return customerService.getOne(id);
    }

    @PostMapping("/dat-hang")
    public String datHangOnline(@RequestBody List<CartDetail> listCartDetail) {
        try {
            billService.datHang(listCartDetail);
            return "Thanh cong";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


}
