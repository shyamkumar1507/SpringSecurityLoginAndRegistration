package com.example.springsecurityloginandregistration.controller;

import com.example.springsecurityloginandregistration.entity.Customer;
import com.example.springsecurityloginandregistration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager; //AuthenticationManager

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Customer customer) {
        boolean isCustomerSaved = customerService.saveCustomer(customer);
        if (isCustomerSaved) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Customer registration failed");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Customer customer) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        boolean isAuthenticated = authentication.isAuthenticated();
        if (isAuthenticated) {
            return ResponseEntity.status(HttpStatus.OK).body("Customer logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Customer login failed");
        }
    }
}
