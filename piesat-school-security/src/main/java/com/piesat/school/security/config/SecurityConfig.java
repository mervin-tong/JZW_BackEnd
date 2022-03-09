package com.piesat.school.security.config;

import com.piesat.school.security.handler.*;
import com.piesat.school.security.service.SpringSecurisyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//springSecurity配置类
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //登录成功处理逻辑
    @Autowired
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    //登录失败处理逻辑
    @Autowired
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    //登出成功处理逻辑
    @Autowired
    CustomizeLogoutSuccessHandler logoutSuccessHandler;
    //登录信息
    @Autowired
    private SpringSecurisyUserService springSecurisyUserService;
    //异常处理(权限拒绝、登录失效等)
    @Autowired
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private CustomizeAccessDeniedHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //登录信息认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(springSecurisyUserService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler)//登录成功处理逻辑
                .failureHandler(authenticationFailureHandler)//登录失败处理逻辑
                .and()
                .authorizeRequests()//指定那些接口需要认证
                .antMatchers("/app/user/addUser",
                        "/app/user/sendEmail",
                        "/app/user/forgetPassword",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/doc.html"
                ).permitAll()//不需要权限直接访问
                .anyRequest().authenticated()//所有请求都需要认证
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)//登出成功处理逻辑
                .deleteCookies("JSESSIONID");//登出之后删除cookie




                //异常处理(权限拒绝、登录失效等)
                http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)//权限拒绝处理逻辑
                .authenticationEntryPoint(authenticationEntryPoint);//匿名用户访问无权限资源时的异常处理

//        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);

    }


}
