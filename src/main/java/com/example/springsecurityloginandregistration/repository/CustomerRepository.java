package com.example.springsecurityloginandregistration.repository;

import com.example.springsecurityloginandregistration.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
}
