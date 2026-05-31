package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.CreateUserDTO;
import com.baul.banking_backend.DTOs.UpdateUserDTO;
import com.baul.banking_backend.enums.Enums.UserRoles;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.User;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllCustomer() {
        return repo.findAll();
    }

    public User getCustomerById(int custId) {
        return  repo.findById(custId).orElse(null);
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();

        return repo.findByCustName(userName)
                .orElseThrow(() -> new ResourceNotfoundException("User Not Found"));
    }

    public User updateCurrentUser(UpdateUserDTO updateUserDTO){
        User user = getCurrentUser();

        if(updateUserDTO.getCustName() != null)
            user.setCustName(updateUserDTO.getCustName());

        if(updateUserDTO.getCustEmail() != null)
            user.setCustEmail(updateUserDTO.getCustEmail());

        if(updateUserDTO.getPassword() != null)
            user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));

        if(updateUserDTO.getCustAge() != null)
            user.setCustAge(updateUserDTO.getCustAge());

        return repo.save(user);
    }

    public void registerCustomer(CreateUserDTO user) {

        User customer = new User();

        customer.setCustName(user.getCustName());
        customer.setCustAge(user.getCustAge());
        customer.setCustEmail(user.getCustEmail());
        customer.setPassword(passwordEncoder.encode(user.getPassword()));
        customer.setRole(UserRoles.USER);
        customer.setActive(true);

        repo.save(customer);
    }

    public User updateCustomer(int custId, UpdateUserDTO user) {

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

        }).orElseThrow(() -> new ResourceNotfoundException("Customer not found"));
    }

    public User activateCustomer(int custId) {
        User customer = repo.findById(custId).orElseThrow(() -> new ResourceNotfoundException("No customer found"));
        customer.setActive(Boolean.TRUE);
        return customer;
    }

    public User deactivateCustomer(int custId) {
        User customer = repo.findById(custId).orElseThrow(() -> new ResourceNotfoundException("No customer found"));
        customer.setActive(Boolean.FALSE);
        return customer;
    }
}
