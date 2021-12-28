package com.test.service.serviceImp;

import com.test.dao.CustomerRepository;
import com.test.model.Customer;
import com.test.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AccountServiceImp accountServiceImp;

    public CustomerServiceImp(CustomerRepository userRepository, AccountServiceImp accountServiceImp) {
        this.customerRepository = userRepository;
        this.accountServiceImp = accountServiceImp;
    }

    /**
     * create new customer
     * @param customer: the appropriate customer
     * @return: created customer
     */
    public Customer addCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * get all customers
     * @return: list of customers
     */
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }


}
