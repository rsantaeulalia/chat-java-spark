package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;

public class TokenValidatorServiceImpl implements TokenValidatorService{

    private static final long EXPIRATION_TIME = 10 * 60 * 1000L;

    private final String jwtSecretKey;
    public static final String BCRYPT_SALT = BCrypt.gensalt(10);


    public TokenValidatorServiceImpl(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCRYPT_SALT);
    }
    public boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
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
