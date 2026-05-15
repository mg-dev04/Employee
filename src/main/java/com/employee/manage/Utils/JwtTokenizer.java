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

@Component
public class JwtTokenizer {
    private final String SECRET = "mangoCitySalemKPT636010mangoCitySalemKPT636010mangoCitySalemKPT636010";
    private final long EXPIRE = 1000 * 60 *60;
    private final Key SecretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String Tokenizer(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SecretKey , SignatureAlgorithm.HS256)
                .compact();
    }
    public String emailGetter( String Token){
        return Jwts.parser()
                .setSigningKey(SecretKey)
                .build()
                .parseSignedClaims(Token)
                .getPayload()
                .getSubject();
    }
    public Boolean Validater(String Token){
        try{
            Jwts.parser()
                    .setSigningKey(SecretKey)
                    .build()
                    .parseSignedClaims(Token);
            return  true;
        }
        catch (JwtException e){
            return false;
        }
    }
}
