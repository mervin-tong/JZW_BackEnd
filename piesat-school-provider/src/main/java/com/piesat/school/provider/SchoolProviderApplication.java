package com.piesat.school.provider;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.smartwork.base.factory.YamlPropertySourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@EnableMethodCache(basePackages = "com.company.mypackage")
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = {"com.piesat.school"})
@MapperScan("com.piesat.school.biz.**.mapper")
@PropertySource(factory = YamlPropertySourceFactory.class, value = {
        //"classpath:datasource/dynamic.yml",
        "classpath:mq/kafka.yml",
        //"classpath:datasource/dynamic-sharding.yml",
        "classpath:datasource/dynamic.yml",
        //"classpath:redis/redisson.yml",
        //"classpath:cache/jetcache.yml",
        "classpath:biz/config.yml"
})
public class SchoolProviderApplication {

    public static void main(String[] args) {
        //SpringApplication.run(LawlietUserProviderApplication.class, args);
        System.setProperty("dubbo.application.logger","slf4j");
        new SpringApplicationBuilder(SchoolProviderApplication.class)
                //.properties("spring.profiles.active=nacos")
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
