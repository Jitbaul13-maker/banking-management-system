package com.baul.banking_backend.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.access-secret}")
    private String accessKey;

    @Value("${jwt.refresh-secret}")
    private String refreshKey;

    public String generateAccessToken(String username) {
        return Jwts
                .builder()
                .subject(username)
                .signWith(getAccessKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*45))
                .compact();
    }

    public SecretKey getAccessKey(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(accessKey)
        );
    }

    public String generateRefreshToken(String username) {
        return Jwts
                .builder()
                .subject(username)
                .signWith(getRefreshKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*1440*7))
                .compact();
    }

    public SecretKey getRefreshKey(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(refreshKey)
        );
    }
}
