package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;

public class LogInService {

    @Autowired
    private AuthenticationManager manager;

    public void verify(LoginReq loginReq) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

        if (authentication.isAuthenticated()) return;
    }
}
