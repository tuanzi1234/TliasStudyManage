package com.example.config;

import com.example.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //注入拦截器
//    @Autowired
//    private DemoInterceptor demoInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/login") //在拦截器中没有判断登录接口，在注册类中就需要配置排除登录接口的方法。
                .excludePathPatterns("/depts/**")
                .excludePathPatterns("/emps/**")
                .excludePathPatterns("/upload");
    }
}
