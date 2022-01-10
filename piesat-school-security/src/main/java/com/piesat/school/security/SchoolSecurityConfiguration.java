package com.piesat.school.security;

import com.piesat.school.security.aspect.PermissionAuthorizeAspect;
import com.piesat.school.security.authorize.SimpleAuthorizeService;
import com.piesat.school.security.properties.AuthorizeProperties;
import com.smartwork.base.factory.YamlPropertySourceFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 唐子超
 * @date 2021-12-13
 * <p>
 * 权限相关Bean
 */
@Configuration
@EnableConfigurationProperties(AuthorizeProperties.class)
@PropertySource(factory = YamlPropertySourceFactory.class, value = {
        "classpath:biz-authorize.yml"
})
public class SchoolSecurityConfiguration {
    @Bean
    public SimpleAuthorizeService authorizeService(ObjectProvider<AuthorizeProperties> authorizeProperties){
        return new SimpleAuthorizeService(authorizeProperties.getIfAvailable());
    }

    @Bean
    public PermissionAuthorizeAspect authorizeAspect(SimpleAuthorizeService authorizeService){
        return new PermissionAuthorizeAspect(authorizeService);
    }
}
