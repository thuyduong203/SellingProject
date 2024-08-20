package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.LoginRequest;
import com.hyperlogy_ban_hang_2.dto.LoginResponseDto;
import com.hyperlogy_ban_hang_2.dto.RegistrationDto;
import com.hyperlogy_ban_hang_2.dto.request.CustomerRegisterReq;
import com.hyperlogy_ban_hang_2.dto.response.BillResponse;
import com.hyperlogy_ban_hang_2.service.BillService;
import com.hyperlogy_ban_hang_2.service.CustomerService;
import com.hyperlogy_ban_hang_2.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"*"})
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillService billService;

    @GetMapping("/bill/get-response")
    public Page<BillResponse> getBillResponse(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo
    ) {
        return billService.getBIllRespone(pageNo);
    }

    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody RegistrationDto registrationDto) throws Exception {
        return authenticationService.register(registrationDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/register-user")
    public String dangKiTaiKhoanUser(@RequestBody CustomerRegisterReq customerRegisterReq) {
        return customerService.dangKiTaiKhoanUser(customerRegisterReq);
    }
}
