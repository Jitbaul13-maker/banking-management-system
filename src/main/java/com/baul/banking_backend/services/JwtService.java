package com.baul.banking_backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.access-secret}")
    private String accessKey;

    @Value("${jwt.refresh-secret}")
    private String refreshKey;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

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

    public String extractRefreshTokenUserName(String refreshToken) {
        return extractRefreshTokenClaims(refreshToken, Claims::getSubject);
    }

    private <T> T extractRefreshTokenClaims(String refreshToken, Function<Claims, T>  claimsResolver) {
        Claims claims = extractRefreshTokenAllClaims(refreshToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractRefreshTokenAllClaims(String refreshToken) {
        return Jwts.parser().verifyWith(getRefreshKey()).build().parseSignedClaims(refreshToken).getPayload();
    }

    public boolean verifyRefreshToken(String refreshToken) {
            try {
                return !isRefreshTokenExpired(refreshToken);
            }
            catch (Exception e) {
                return false;
            }
    }

    public boolean isRefreshTokenExpired(String refreshToken){
        return refreshTokenExpiry(refreshToken).before( new Date());
    }

    public Date refreshTokenExpiry(String refreshToken){
        return extractRefreshTokenClaims(refreshToken, Claims::getExpiration);
    }
}
