package com.example.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
//2
public class JwtService {

    private static final String SECRET_KEY = "OPwNiZ7PrtL6GNvmydXXsXA9Xohoc0nfQ0Y9YeiQ+Js=";

    public String generateToken(UserDetails userDetails) { //JWT oluşturulur.
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("roles", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .addClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) //tokenin geçerlilik süresi 2 saat
                .signWith(getKey(), SignatureAlgorithm.HS256) //Tokenı oluştururken olçuşturma ve çözme keyi. BU keyle oluşur ve çözülür
                .compact();

    }

    public Object getClaimsByKey(String token, String key) {
        Claims claims = getClaims(token);
        return claims.get(key);
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims;
    }

    public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
        //tokenı çözmek için bu metodu yazarız
        Claims claims = getClaims(token);

        return claimsFunction.apply(claims);
    }

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameByToken(String token) {
        return exportToken(token, claims -> claims.getSubject());
    }

    public boolean isTokenNotExpired(String token) {
        Date expiredDate = exportToken(token, claims -> claims.getExpiration()); //tokenin bittigi süryi alırız
        return new Date().before(expiredDate);
    }

}
