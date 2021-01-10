package com.pclogo.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtToken {
    public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createJWT(String id, String subject, long ttlMillis)
    {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(key);
        if(ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static Claims parseJWT(String jwt)
    {
        jwt = jwt.replace("Bearer", "");
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
    }

}
