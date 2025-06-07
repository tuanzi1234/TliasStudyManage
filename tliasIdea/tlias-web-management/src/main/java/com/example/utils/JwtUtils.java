package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 签名密钥（与测试类一致）
    private static final String SECRET_KEY = "dHVhbnpp";
    // 默认过期时间1小时（毫秒）
    private static final long DEFAULT_EXPIRATION = 12 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌（使用默认过期时间）
     * @param claims 自定义数据
     * @return JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用HS256算法和密钥生成JWT令牌
                .addClaims(claims) // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + DEFAULT_EXPIRATION)) //  设置令牌过期时间
                .compact(); //  生成令牌
    }

    /**
     * 解析JWT令牌
     * @param token JWT令牌字符串
     * @return 解析后的声明体
     * @throws io.jsonwebtoken.JwtException 如果令牌无效/过期
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) //  设置密钥
                .parseClaimsJws(token) //  解析JWT令牌
                .getBody(); //获取自定义数据
    }
}
