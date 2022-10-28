package com.chen.star.config;

import com.chen.star.base.exception.BaseException;
import com.chen.star.base.filter.JwtAuthenticationFilter;
import com.chen.star.base.properties.IgnoreProperties;
import com.chen.star.base.service.AuthLoginService;
import com.chen.star.config.bean.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.chen.star.base.exception.Status;

@Configuration      // 告诉spring 为配置类
@EnableWebSecurity
@EnableConfigurationProperties(IgnoreProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthLoginService authLoginService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private IgnoreProperties ignoreProperties;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated()   //任意请求,认证后可访问
                .and().csrf().disable()       //防止csrf攻击
                // Session 管理
                .sessionManagement()
//                .maximumSessions(1).maxSessionsPreventsLogin(true);
                // 因为使用了JWT，所以这里不管理Session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    throw new BaseException(Status.FORBIDDEN);
                });
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 放行所有不需要登录就可以访问的请求，参见 AuthController
     * 也可以在 {@link #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)} 中配置
     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.GET, ignoreProperties.getGet())
                .antMatchers(HttpMethod.POST, ignoreProperties.getPost())
                .antMatchers(HttpMethod.DELETE, ignoreProperties.getDelete())
                .antMatchers(HttpMethod.PUT, ignoreProperties.getPut())
                .antMatchers(HttpMethod.HEAD, ignoreProperties.getHead())
                .antMatchers(HttpMethod.PATCH, ignoreProperties.getPatch())
                .antMatchers(HttpMethod.OPTIONS, ignoreProperties.getOptions())
                .antMatchers(HttpMethod.TRACE, ignoreProperties.getTrace())
                .antMatchers(ignoreProperties.getPattern());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setPasswordEncoder(encoder());
        impl.setUserDetailsService(authLoginService);
        // impl.setUserDetailsPasswordService();
        // impl.setForcePrincipalAsString();
        // impl.setHideUserNotFoundExceptions(false);
        // impl.setMessageSource();
        // TODO: 2021/6/6 changjin wei(魏昌进) 需要缓存啊
        // impl.setUserCache();
        // impl.setPreAuthenticationChecks();
        // impl.setPostAuthenticationChecks();
        // impl.setAuthoritiesMapper();
        return impl;
    }

}
