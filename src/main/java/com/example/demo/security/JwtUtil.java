package com.example.demo.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final KmsService kmsService;

    public JwtUtil(KmsService kmsService) {
        this.kmsService = kmsService;
    }

    private byte[] getKey() {
        String secret = kmsService.getDecryptedSecret();
        return secret.getBytes();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(Keys.hmacShaKeyFor(getKey()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
