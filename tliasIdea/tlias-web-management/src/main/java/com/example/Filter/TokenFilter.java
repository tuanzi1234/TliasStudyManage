package com.example.Filter;

import com.example.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * filter令牌校验流程
 */
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    //声明Logger对象
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1.获取请求路径
        //将ServletRequest转换成HttpServletRequest, 将ServletResponse转换成HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =  (HttpServletResponse) servletResponse;
        //获取请求路径
        String requestURI = request.getRequestURI();

        // 2.判断是否时登录请求，若是登录请求(请求中包含关键字/login)，则放行
        if (requestURI.contains("/login")) {
            logger.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }
        // 3.获取Token
        String token = request.getHeader("token");

        //  4.判断Token是否为空，为空则返回未登录信息,返回401状态码
        if (token == null || token.isEmpty()) {
            logger.info("Token为空，返回未登录信息");
            response.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5.若Token存在，则解析Token，若Token解析失败，则返回未授权信息,返回401状态码
        try {
            JwtUtils.parseJwt(token);
        }catch ( Exception e){
            logger.info("Token解析失败，返回未授权信息");
            response.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //  6.若Token解析成功，则放行
        logger.info("Token解析成功，放行");
        filterChain.doFilter(request, response);
    }
}
