package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByEmailAndPassword(String email, String pass);

    Optional<Customer> findCustomerByEmail(String email);
}
