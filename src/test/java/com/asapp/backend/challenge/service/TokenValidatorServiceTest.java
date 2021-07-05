package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Date;

public class TokenValidatorServiceTest implements TokenValidatorService {

    private static final long EXPIRATION_TIME = 10 * 60 * 1000L;

    private final String jwtSecretKey;

    public TokenValidatorServiceTest(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public String generateToken(User user) {
        DefaultClaims claims = new DefaultClaims();
        claims.setSubject(user.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            return !Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token)
                    .getBody().getSubject().isEmpty();
        } catch (Exception e) {
            return false;
        }

    }
}
