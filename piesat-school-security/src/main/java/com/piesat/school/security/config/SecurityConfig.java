package com.piesat.school.security.config;

import com.piesat.school.security.filter.JWTAuthenticationFilter;
import com.piesat.school.security.filter.JWTAuthorizationFilter;
import com.piesat.school.security.handler.*;
import com.piesat.school.security.service.SpringSecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.FlushMode;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.annotation.Resource;

//springSecurity配置类
@Configuration
@EnableWebSecurity
@EnableRedisHttpSession(redisNamespace = "spring:session:myframe",maxInactiveIntervalInSeconds = 5000,flushMode = FlushMode.ON_SAVE)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //登录成功处理逻辑
    @Resource
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    //登录失败处理逻辑
    @Resource
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    //登出成功处理逻辑
    @Resource
    CustomizeLogoutSuccessHandler logoutSuccessHandler;
    //登录信息
    @Resource
    private SpringSecurityUserService springSecurityUserService;
    //异常处理(权限拒绝、登录失效等)
    @Resource
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private CustomizeAccessDeniedHandler accessDeniedHandler;
//    @Resource
//    private CustomSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    //redis获取sessionRepository
    @Autowired
    @Lazy
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //登录信息认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(springSecurityUserService).passwordEncoder(passwordEncoder());
    }
    @Override
    @Bean
    //注入authenticationManager
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    //注入

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new CustomSessionInformationExpiredStrategy();
    }





//    @Autowired
//    public SecurityConfig(JsonSessionInformationExpiredHandler sessionInformationExpiredStrategy){
//        this.sessionInformationExpiredStrategy=sessionInformationExpiredStrategy;
//    }


    // 用于在集群环境下控制会话并发的会话注册表实现
    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry(){
        return new SpringSessionBackedSessionRegistry<>(sessionRepository);
    }

//    @Bean
//    public SessionRegistry getSessionRegistry(){
//        SessionRegistry sessionRegistry=new SessionRegistryImpl();
//        return sessionRegistry;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .formLogin()
                .loginProcessingUrl("/login")
                .successForwardUrl("/login/success")
                .failureForwardUrl("/login/failure")
//                .disable()
//                .and()
//                .authorizeRequests()//指定那些接口需要认证
//                .antMatchers("/app/user/addUser",
//                        "/app/user/sendEmail",
//                        "/app/user/forgetPassword",
//                        "/v2/api-docs",
//                        "/webjars/**",
//                        "/swagger-resources/**",
//                        "/swagger-ui.html",
//                        "/doc.html"
//                ).permitAll()//不需要权限直接访问
//                .anyRequest().authenticated()//所有请求都需要认证
//                .and()
//                .authorizeRequests()
//                .antMatchers("/app/dataInf/mergeFirstClass",
//                        "/app/dataInf/mergeSecClass",
//                        "/app/dataInf/deleteDataClassification",
//                        "/app/dataInf/saveDataClassification",
//                        "/app/dataInf/deleteGenerationMode",
//                        "/app/dataInf/mergeGenerationMode",
//                        "/app/orderfrom/assign",
//                        "/app/orderfrom/release",
//                        "/app/order/orderfromDelete",
//                        "/app/order/checkInOrOut",
//                        "/app/topic/del",
//                        "/app/topic/saveOrUpdate",
//                        "/app/uploadPermissions/setApprover",
//                        "/app/uploadPermissions/cleanApprover",
//                        "/app/uploadPermissions/closeUpPermissions",
//                        "/app/information/del",
//                        "/app/uploadPermissions/detail",
//                        "/app/uploadPermissions/checkPermissions"
//                        )
//                .authenticated()//所有请求都需要认证
//                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),sessionRegistry()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)//登出成功处理逻辑
                .deleteCookies("JSESSIONID")//登出之后删除cookie
                .and()
//        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)//不阻止登录，覆盖
                .expiredSessionStrategy(new CustomSessionInformationExpiredStrategy());

                //异常处理(权限拒绝、登录失效等)
                http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)//权限拒绝处理逻辑
                .authenticationEntryPoint(authenticationEntryPoint);//匿名用户访问无权限资源时的异常处理

//        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
//        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);

    }


}
