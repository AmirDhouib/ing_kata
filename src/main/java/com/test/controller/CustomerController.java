package com.test.controller;


import com.test.model.Customer;
import com.test.service.CustomerService;
import com.test.service.serviceImp.CustomerServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Two REST services: the first allows to display User details and the second one allows to register a User.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerServiceImp customerServiceImp) {
        this.customerService = customerServiceImp;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getUserById() {
        List<Customer> customers = customerService.getCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Customer> register(@RequestParam String name, @RequestParam String address,
                                             @RequestParam String cardNumber, @RequestParam String pin
    ) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setCardNumber(cardNumber);
        customer.setPin(pin);
        Customer createdCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }


}
