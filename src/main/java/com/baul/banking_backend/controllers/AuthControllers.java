package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.AuthTokenDTO;
import com.baul.banking_backend.DTOs.CreateUserDTO;
import com.baul.banking_backend.DTOs.LogInResDTO;
import com.baul.banking_backend.DTOs.LoginReqDTO;
import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.services.JwtService;
import com.baul.banking_backend.services.LogInService;
import com.baul.banking_backend.services.CustomerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllers {

    private CustomerService userservice;
    private LogInService logInService;
    private JwtService jwtService;

    public AuthControllers(CustomerService userservice, LogInService logInService, JwtService jwtService) {
        this.userservice = userservice;
        this.logInService = logInService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer( @Valid @RequestBody CreateUserDTO user){
        userservice.registerCustomer(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LogInResDTO> loginCustomer(@Valid @RequestBody LoginReqDTO loginReq,
                                                     HttpServletResponse response){
        AuthTokenDTO tokens = logInService.verify(loginReq);

        Cookie cookie = new Cookie("refreshToken", tokens.getRefreshToken());

        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(60*60*1440*7);

        response.addCookie(cookie);

        return ResponseEntity.ok(new LogInResDTO(tokens.getAccessToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LogInResDTO> refresh(@CookieValue(value = "refreshToken", required = false) String refreshToken) {

        if (refreshToken == null || !jwtService.verifyRefreshToken(refreshToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userName = jwtService.extractRefreshTokenUserName(refreshToken);

        String newAccessToken = jwtService.generateAccessToken(userName);

        return ResponseEntity.ok(new LogInResDTO(newAccessToken));
    }
}
