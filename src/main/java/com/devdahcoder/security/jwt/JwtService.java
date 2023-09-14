package com.devdahcoder.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECURITY_KEY = "9C1u3g8B+V6kBrH9JJs42QaR9JEcry0pYUXQQz/2V/ht1xgkkuoI96MIBW2e/qxTubWNSRJf3uTkBLPzSqY80mtoIanUrvulP9A1qWS7ZBx/tDlFS/qR/ozBXYOpAkSBrl1DhM6q0OOX1zsKmV0rvcckDWDGys8nuvpb1IW4soeWhlRC8Yw8BL74CP+B7EOaqdGo0lE1aIDr2RhMQHoV+70CcYPTW2FuWGJ6qDaonTXXk9Ets7Q3hj4HyF0wCMiR9LwgGUh1e6D68w5KmbmYi2/I9Qq1R0IjdGy4Y5ywOjLgcxJTZYzBf6OvexYctpBc+ml7fA/NtHLQZvnxLgbGViuQVnT2MMkNQknw+v0MNe0=";

    public String extractUsername(String jwtToken) {

        return this.extractClaim(jwtToken, Claims::getSubject);

    }

    public <T> T extractClaim(String jwtToken, @NotNull Function<Claims, T> claimsTFunction) {

        final Claims claims = this.extractAllClaims(jwtToken);

        return claimsTFunction.apply(claims);

    }

    private Claims extractAllClaims(String jwtToken) {

        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

    }

//    public String generateJwtToken(UserDetails userDetails) {
//
//        return this.generateJwtToken(new HashMap<>(), userDetails);
//
//    }

    public String generateJwtToken(String username) {

        return this.generateJwtToken(new HashMap<>(), username);

    }

    public String generateJwtToken(Map<String, Object> claims, @NotNull String username) {

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer("devdahcoder")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

//    public String generateJwtToken(Map<String, Object> claims, @NotNull UserDetails userDetails) {
//
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuer("devdahcoder")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//
//    }

    public boolean isTokenValid(String jwtToken, @NotNull UserDetails userDetails) {

        final String username = this.extractUsername(jwtToken);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);

    }

    public boolean isTokenExpired(String jwtToken) {

        return extractExpiration(jwtToken).before(new Date());

    }

    public Date extractExpiration(String jwtToken) {

        return extractClaim(jwtToken, Claims::getExpiration);

    }

    public Key getSignInKey() {

        byte [] byteKey = Decoders.BASE64.decode(SECURITY_KEY);

        return Keys.hmacShaKeyFor(byteKey);

    }

}
