package com.test.service;

import com.test.model.Customer;

import java.util.List;

public interface CustomerService {

    /**
     * create new customer
     * @param customer: the appropriate customer
     * @return: created customer
     */
    Customer addCustomer(final Customer customer);

    /**
     * get all customers
     * @return: list of customers
     */
    List<Customer> getCustomer();
}
