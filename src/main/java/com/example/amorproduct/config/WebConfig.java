package com.example.amorproduct.config;

import com.example.amorproduct.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 * 用于配置拦截器、跨域等全局Web设置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加日志拦截器
        registry.addInterceptor(new LoggingInterceptor())
                .addPathPatterns("/**")  // 拦截所有请求
                .excludePathPatterns(    // 排除不需要日志的路径
                        "/error",
                        "/favicon.ico",
                        "/static/**",
                        "/public/**"
                );
    }
}

