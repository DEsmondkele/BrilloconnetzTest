package org.example.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {


    private static final String SECRET_KEY = "Test-secret";

    @Test
    public void testGenerateJwt() {
        String jwt = JwtUtils.generateJwt();
        assertNotNull(jwt);
        assertTrue(jwt.length() > 0);
    }

    @Test
    public void testVerifyJwtValid() {
        String jwt = JwtUtils.generateJwt();
        String result = JwtUtils.verifyJwt(jwt);
        assertEquals("verification pass", result);
    }

    @Test
    public void testVerifyJwtInvalid() {
        // I Created an invalid JWT (e.g., by modifying the token)
        String invalidJwt = "invalid-jwt-token";
        String result = JwtUtils.verifyJwt(invalidJwt);
        assertEquals("verification fails", result);
    }

    @Test
    public void testVerifyJwtExpired() {
        String expiredJwt = Jwts.builder()
                .setSubject("user")
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() - 1000)) // Set to a past date
                .compact();
        String result = JwtUtils.verifyJwt(expiredJwt);
        assertEquals("verification fails", result);
    }
}