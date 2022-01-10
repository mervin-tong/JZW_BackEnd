package com.piesat.school.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author 唐子超
 * @date 2021-12-13
 * <p>
 * 用于简单配置可授权用户ID的配置
 * 后期替换为RBAC方式进行授权验证
 */
@Setter
@Getter
@ConfigurationProperties(prefix = AuthorizeProperties.PREFIX)
public class AuthorizeProperties {
    public static final String PREFIX = "school.biz.authorize";
    //可被授权的用户IDS
    private List<String> users;
}
