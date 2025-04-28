package com.example.springsecurityloginandregistration.controller;

import com.example.springsecurityloginandregistration.entity.Customer;
import com.example.springsecurityloginandregistration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerGraphQL {

    @Autowired
    private CustomerService customerService;

    @QueryMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @QueryMapping
    public Customer getCustomerByEmail(@Argument String email) {
        return customerService.getCustomerByEmail(email);
    }



}
