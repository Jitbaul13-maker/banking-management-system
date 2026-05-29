package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.AuthTokenDTO;
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

    public AuthTokenDTO verify(LoginReqDTO loginReq) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

        if (authentication.isAuthenticated()) {
            String accessToken = jwtService
                    .generateAccessToken(loginReq.getUsername());

            String refreshToken = jwtService
                    .generateRefreshToken(loginReq.getUsername());

            return new AuthTokenDTO(accessToken, refreshToken);
        }

        throw new RuntimeException("Authentication Failed");
    }
}
