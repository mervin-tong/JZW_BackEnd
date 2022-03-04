package com.piesat.school.biz.test;

//import com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration;

import com.smartwork.base.factory.YamlPropertySourceFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

//@EnableTransactionManagement
//@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//@EnableAsync
@SpringBootApplication
//@EnableConfigurationProperties(UserProviderRuntimeProperties.class)
//@EnableElasticsearchRepositories(basePackages = "com.piesat.school.biz.es")
@ComponentScan(basePackages = {"com.piesat.school"})
@MapperScan("com.piesat.school.biz.**.mapper")
//@EnableMethodCache(basePackages = "com.piesat.school")
//@EnableCreateCacheAnnotation
@PropertySource(factory = YamlPropertySourceFactory.class, value = {
        "classpath:datasource/dynamic.yml",
        //"classpath:datasource/dynamic-sharding.yml",
        //"classpath:redis/redisson.yml",
        "classpath:biz/config.yml"
})
public class SchoolBizTestApplication {

    public static void main(String[] args) {
        //SpringApplication.run(LawlietUserProviderApplication.class, args);
        new SpringApplicationBuilder(SchoolBizTestApplication.class)
                //.properties("spring.profiles.active=nacos")
                .web(WebApplicationType.NONE)
                .run(args);
    }


//    @Test
//    public void test1(){
//        redisUtil.set("1","2");
//    }

}
