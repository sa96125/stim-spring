package com.sa96125.stim.common.jwt;

import com.sa96125.stim.common.api.exception.custom.AuthenticationFailedException;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private static String jwtSecret;
    
    @Value("${jwt.expiration}")
    private static long jwtExpirationInMs;
    
    public static String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs * 12))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public static String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid token signature: {}", e.getMessage());
            throw new AuthenticationFailedException("Invalid token signature.");
        } catch (ExpiredJwtException e) {
            log.info("Token expired: {}", e.getMessage());
            throw new AuthenticationFailedException("Token expired.");
        } catch (MalformedJwtException e) {
            log.info("Malformed token: {}", e.getMessage());
            throw new AuthenticationFailedException("Malformed token.");
        } catch (Exception e) {
            log.info("Token validation failed: {}", e.getMessage());
            throw new AuthenticationFailedException("Token validation failed.");
        }
    }
    
    public static String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
    public static String generateAccessToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", email);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}