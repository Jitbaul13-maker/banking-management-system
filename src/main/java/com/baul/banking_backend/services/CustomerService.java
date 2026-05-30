package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.CreateUserDTO;
import com.baul.banking_backend.DTOs.UpdateUserDTO;
import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    public void registerCustomer(CreateUserDTO user) {

        Customer customer = new Customer();

        customer.setCustName(user.getCustName());
        customer.setCustAge(user.getCustAge());
        customer.setCustEmail(user.getCustEmail());
        customer.setPassword(passwordEncoder.encode(user.getPassword()));
        customer.setActive(true);

        repo.save(customer);
    }

    public Customer updateCustomer(int custId, UpdateUserDTO user) {

        return repo.findById(custId).map(existingCustomer -> {

            if(user.getCustName() != null)
                existingCustomer.setCustName(user.getCustName());

            if(user.getCustEmail() != null)
                existingCustomer.setCustEmail(user.getCustEmail());

            if(user.getPassword() != null)
                existingCustomer.setPassword(passwordEncoder.encode(user.getPassword()));

            if(user.getCustAge() != null)
                existingCustomer.setCustAge(user.getCustAge());

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
