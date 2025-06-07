package com.example.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    //声明Logger对象
    private static final Logger logger = LoggerFactory.getLogger(DemoFilter.class);

    //  初始化方法，只在Web服务器启动的时候执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("DemoFilter初始化");
    }

    //  过滤方法，拦截到请求就执行，每次请求都会执行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("DemoFilter过滤");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 销毁方法，只在Web服务器关闭的时候执行一次
    @Override
    public void destroy() {
        logger.info("DemoFilter销毁");
    }
}
