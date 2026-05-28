package com.baul.banking_backend.services;

import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Customer> getAllCustomer() {
        return repo.findAll();
    }

    public Customer getCustomerById(int custId) {
        return  repo.findById(custId).orElse(null);
    }

    public void registerCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        repo.save(customer);
    }

    public Customer updateCustomer(int custId, Customer customer) {

        return repo.findById(custId).map(existingCustomer -> {

            if(customer.getCustName() != null)
                existingCustomer.setCustName(customer.getCustName());

            if(customer.getCustEmail() != null)
                existingCustomer.setCustEmail(customer.getCustEmail());

            if(customer.getPassword() != null)
                existingCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));

            if(customer.getCustAge() != null)
                existingCustomer.setCustAge(customer.getCustAge());

            return repo.save(existingCustomer);

        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer activateCustomer(int custId) {
        Customer customer = repo.findById(custId).orElseThrow(() -> new RuntimeException("No customer found"));
        customer.setActive(Boolean.TRUE);
        return customer;
    }

    public Customer deactivateCustomer(int custId) {
        Customer customer = repo.findById(custId).orElseThrow(() -> new RuntimeException("No customer found"));
        customer.setActive(Boolean.FALSE);
        return customer;
    }
}
