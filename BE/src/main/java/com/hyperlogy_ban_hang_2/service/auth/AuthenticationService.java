package com.hyperlogy_ban_hang_2.service.auth;

import com.hyperlogy_ban_hang_2.dto.LoginRequest;
import com.hyperlogy_ban_hang_2.dto.LoginResponseDto;
import com.hyperlogy_ban_hang_2.dto.RegistrationDto;
import com.hyperlogy_ban_hang_2.entity.Admin;
import com.hyperlogy_ban_hang_2.entity.Customer;
import com.hyperlogy_ban_hang_2.entity.Role;
import com.hyperlogy_ban_hang_2.repository.AdminRepository;
import com.hyperlogy_ban_hang_2.repository.CustomerRepository;
import com.hyperlogy_ban_hang_2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public String register(RegistrationDto registrationDto) throws Exception {
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        Role role = roleRepository.findById(registrationDto.getRoleId()).get();
        //fix cung du lieu bang role: id = 1 => admin, == 2 => customer
        if (registrationDto.getRoleId() == 1) {
            Admin admin = Admin.builder()
                    .email(registrationDto.getEmail())
                    .password(encodedPassword)
                    .role(role)
                    .build();
            adminRepository.save(admin);
        } else if (registrationDto.getRoleId() == 2) {
            Customer customer = Customer.builder()
                    .email(registrationDto.getEmail())
                    .password(encodedPassword)
                    .role(role)
                    .build();
            customerRepository.save(customer);
        } else {
            return null;
        }
        return registrationDto.getEmail();
    }

    public LoginResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            if (adminRepository.findAdminByEmail(loginRequest.getEmail()).isPresent()) {
                Admin admin = adminRepository.findAdminByEmail(loginRequest.getEmail()).get();
                return new LoginResponseDto(admin.getId(), admin.getCode(), loginRequest.getEmail(), admin.getRole().getAuthority(), token);
            } else if ((customerRepository.findCustomerByEmail(loginRequest.getEmail()).isPresent())) {
                Customer customer = customerRepository.findCustomerByEmail(loginRequest.getEmail()).get();
                return new LoginResponseDto(customer.getId(), customer.getCode(), loginRequest.getEmail(), customer.getRole().getAuthority(), token);
            } else {
                return null;
            }
        } catch (AuthenticationException e) {
            return null;
        }
    }
}
