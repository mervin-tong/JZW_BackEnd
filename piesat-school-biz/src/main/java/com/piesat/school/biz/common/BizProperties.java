package com.piesat.school.biz.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lawliet
 * @date 2020-07-24
 * <p>
 * 业务配置入口路径
 */
//@Data
@ConfigurationProperties(prefix = BizProperties.PREFIX)
public class BizProperties {
    public static final String PREFIX = "school.biz";

}
