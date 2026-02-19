package com.julien.utils;

import io.jsonwebtoken.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    public static String createJwt(String secretKey, Long ttlMillis, Map<String,Object> claims){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        JwtBuilder builder = Jwts.builder()

                .setClaims(claims)

                .signWith(signatureAlgorithm,secretKey.getBytes(StandardCharsets.UTF_8))

                .setExpiration(exp);

        return  builder.compact();
    }

    public static Claims parseJWT(String secretKey,String token) {

        Claims claims = Jwts.parser()

                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))

                .parseClaimsJws(token)  // 修改这里：使用 parseClaimsJws 而不是 parseClaimsJwt

                .getBody();

        return claims;
    }


}
