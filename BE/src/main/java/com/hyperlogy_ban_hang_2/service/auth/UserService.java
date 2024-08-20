package com.hyperlogy_ban_hang_2.service.auth;

import com.hyperlogy_ban_hang_2.repository.AdminRepository;
import com.hyperlogy_ban_hang_2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (adminRepository.findAdminByEmail(username).isPresent()) {
            return adminRepository.findAdminByEmail(username).orElse(null);
        } else if (customerRepository.findCustomerByEmail(username).isPresent()) {
            return customerRepository.findCustomerByEmail(username).orElse(null);
        } else {
            System.out.println("Khomng tim thay");
            return null;
        }
    }
}
