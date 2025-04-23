package com.example.springsecurityloginandregistration.service.security;

import com.example.springsecurityloginandregistration.entity.Customer;
import com.example.springsecurityloginandregistration.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);
        return new User(customer.getEmail(), customer.getPassword(), Collections.emptyList());
    }
}
