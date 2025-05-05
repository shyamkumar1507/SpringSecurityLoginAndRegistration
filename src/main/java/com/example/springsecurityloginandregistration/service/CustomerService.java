package com.example.springsecurityloginandregistration.service;

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
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean saveCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        //Changes, this is a test to show siddu
         Customer savedCustomer =   customerRepository.save(customer);
        return savedCustomer.getCid() != null;
    }

    public List<Customer> getAllCustomers() {return customerRepository.findAll();}

    public Customer getCustomerByEmail(String email) {return customerRepository.findByEmail(email);}

}
