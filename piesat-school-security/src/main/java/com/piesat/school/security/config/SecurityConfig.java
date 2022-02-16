package com.piesat.school.security.config;

import com.piesat.school.security.handler.*;
import com.piesat.school.security.service.SpringSecurisyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //登录成功处理逻辑
    @Autowired
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    //登录失败处理逻辑
    @Autowired
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    //访问决策管理器
    @Autowired
    CustomizeAccessDecisionManager accessDecisionManager;

    //实现权限拦截
    @Autowired
    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

    //登出成功处理逻辑
    @Autowired
    CustomizeLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Autowired
    private SpringSecurisyUserService springSecurisyUserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(springSecurisyUserService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
                        return o;
                    }
                }).
                //登出
                and().logout().
                permitAll().//允许所有用户
                logoutSuccessHandler(logoutSuccessHandler).//登出成功处理逻辑
                deleteCookies("JSESSIONID").//登出之后删除cookie
                and()
                .formLogin()
                .permitAll()
                .successHandler(authenticationSuccessHandler)//登录成功处理逻辑
                .failureHandler(authenticationFailureHandler)//登录失败处理逻辑
                .and()
                .logout().permitAll(); //注销行为任意访问
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);

    }


}