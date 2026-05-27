package com.baul.banking_backend.services;

import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private CustomerRepo repo;

    public List<Customer> getAllCustomer() {
        return repo.findAll();
    }

    public Customer getCustomerById(int custId) {
        return  repo.findById(custId).orElse(null);
    }

    public void registerCustomer(Customer customer) {
        repo.save(customer);
    }

    public void deleteCustomer(int custId) {
        repo.deleteById(custId);
    }

    public Customer updateCustomer(int custId, Customer customer) {
        return repo.
                findById(custId).map(existingCustomer -> {
                    existingCustomer.setAccountDetails(customer.getAccountDetails());
                    existingCustomer.setActive(customer.getActive());
                    existingCustomer.setCustAge(customer.getCustAge());
                    existingCustomer.setPassword(customer.getPassword());
                    existingCustomer.setCustName(customer.getCustName());
                    existingCustomer.setCustEmail(customer.getCustEmail());
                    existingCustomer.setDepositDetails(customer.getDepositDetails());

                    return repo.save(existingCustomer);
                }).orElse(null);
    }
}
