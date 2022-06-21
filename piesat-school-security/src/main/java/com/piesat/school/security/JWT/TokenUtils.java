package com.piesat.school.security.JWT;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    // TOKEN 过期时间
    public static final long EXPIRATION = 1000 * 60 * 5; // 三十分钟

    public static final String APP_SECRET_KEY = "secret";

    private static final String ROLE_CLAIMS = "role";

    /**
     * 生成token
     *
     * @param username
     * @param role
     * @return
     */
    public static String createToken(String username, String role) {

        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET_KEY).compact();
        return token;
    }


    /**
     * 获取当前登录用户用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 获取当前登录用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    /**
     * 获解析token中的信息
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 检查token是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) throws RuntimeException {
        try {
            Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.getExpiration().after(new Date());
        }catch (ExpiredJwtException e){
            return false;
        }
    }
}
