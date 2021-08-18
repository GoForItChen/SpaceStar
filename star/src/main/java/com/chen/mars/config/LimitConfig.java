package com.chen.mars.config;

import com.chen.mars.config.interceptor.IPLimitHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class LimitConfig implements WebMvcConfigurer
{
    @Autowired
    private IPLimitHandlerInterceptor limitHandlerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(limitHandlerInterceptor)//
                .addPathPatterns("/**") //
                .excludePathPatterns( //
                        "/auth/login", //
                        "/auth/logout", //
                        "/auth/*",
                        "/auth/**",
                        "/test/*",
                        "/v3/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/swagger-ui/*"
                );
    }

    /**
     * 跨域访问配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

}
