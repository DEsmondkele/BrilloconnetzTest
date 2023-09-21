package org.example.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
    private static final String SECRET_KEY = "your-secret-key";

    public static String generateJwt() {
        // Generate JWT token
        return Jwts.builder()
                .setSubject("user")
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String verifyJwt(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
            return "verification pass";
        } catch (Exception e) {
            return "verification fails";
        }
    }
}
