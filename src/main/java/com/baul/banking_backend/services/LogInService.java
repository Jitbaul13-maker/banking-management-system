package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.LoginReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

    private AuthenticationManager manager;
    private JwtService jwtService;

    public LogInService(AuthenticationManager manager, JwtService jwtService) {
        this.manager = manager;
        this.jwtService = jwtService;
    }

    public String verify(LoginReqDTO loginReq) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

        if (authentication.isAuthenticated()) return jwtService.generateAccessToken(loginReq.getUsername());
        return "Authentication Failed";
    }
}
