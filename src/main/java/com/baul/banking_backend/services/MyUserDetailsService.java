package com.baul.banking_backend.services;

import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.User;
import com.baul.banking_backend.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User customer = repo.findByCustName(username)
                .orElseThrow(() -> new ResourceNotfoundException("User not found"));

        return new MyUserPrinciple(customer);
    }
}
