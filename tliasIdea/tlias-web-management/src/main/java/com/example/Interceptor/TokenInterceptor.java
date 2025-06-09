package com.example.Interceptor;

import com.example.utils.CurrentHolder;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    //声明Logger对象
    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求路径(在Interceptor拦截器中，第一二两步可以直接跳过)
//        String requestURI = request.getRequestURI();
//
//        // 2.判断是否时登录请求，若是登录请求(请求中包含关键字/login)，则放行
//        if (requestURI.contains("/login")) {
//            logger.info("登录请求，放行");
//            return true;
//        }
        // 3.获取Token
        String token = request.getHeader("token");

        //  4.判断Token是否为空，为空则返回未登录信息,返回401状态码
        if (token == null || token.isEmpty()) {
            logger.info("Token为空，返回未登录信息");
            response.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 5.若Token存在，则解析Token，若Token解析失败，则返回未授权信息,返回401状态码
        try {
            // 解析token并获取员工ID
            Claims claims = JwtUtils.parseJwt(token);
            Integer employeeId = (Integer) claims.get("id");
            // 存储到ThreadLocal
            CurrentHolder.setOperateId(employeeId);
            logger.info("成功获取员工ID: {}", employeeId);
        }catch ( Exception e){
            logger.info("Token解析失败，返回未授权信息");
            response.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //  6.若Token解析成功，则放行
        logger.info("Token解析成功，放行");
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        logger.info("请求结束，清理ThreadLocal");
        // 请求结束后清理ThreadLocal
        CurrentHolder.removeOperateLog();
    }
}
