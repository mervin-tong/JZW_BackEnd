package com.piesat.school.biz.common;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lawliet
 * @date 2021-08-31
 * <p>
 * 描述内容
 */
@Configuration
@EnableConfigurationProperties(BizProperties.class)
public class BizConfiguration {
    /*@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }*/
}
