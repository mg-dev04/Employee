package com.employee.manage.Utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.security.auth.Subject;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtTokenizer {
    @Value("${JWT_SECRET:mangoCitySalemKPT636010mangoCitySalemKPT636010mangoCitySalemKPT636010}")
    private String SECRET;

    private final long EXPIRE = 1000 * 60 *60;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String Tokenizer(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(getSecretKey() , SignatureAlgorithm.HS256)
                .compact();
    }
    public String emailGetter( String Token){
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseSignedClaims(Token)
                .getPayload()
                .getSubject();
    }
    public Boolean Validater(String Token){
        try{
            Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseSignedClaims(Token);
            return  true;
        }
        catch (JwtException e){
            return false;
        }
    }
}
