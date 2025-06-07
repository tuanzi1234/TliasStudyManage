package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    //生成JWT
    @Test
    public void testGenerateJwt() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("username", "admin");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "dHVhbnpp")
                .addClaims(data) //添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置过期时间
                .compact();//生成JWT令牌
        System.out.println("生成JWT成功:" + jwt);
    }

    //解析JWT
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0OTMwODQ1MH0.Xa-rF1HYXtXdk_Dch5skhV7xKYq1rT-732X8SnQcD3Q";
        Claims claims = Jwts.parser()
                .setSigningKey("dHVhbnpp") // 设置签名的密钥
                .parseClaimsJws(token)//解析JWT令牌
                .getBody();//获取自定义数据
        System.out.println(claims);
    }
}
