package com.piesat.school.rest;

import com.smartwork.base.factory.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@MapperScan("com.piesat.school.security")
@SpringBootApplication
//@EnableConfigurationProperties(UserRestRuntimeProperties.class)
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = {"com.piesat.school"})
@PropertySource(factory = YamlPropertySourceFactory.class, value = {
        "classpath:biz/config.yml",
        "classpath:knife/knife.yml"
        //"classpath:redis/redisson.yml"
})
public class SchoolRestApplication {

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LawlietUserRestApplication.class);
    }*/

    public static void main(String[] args) {
        //SpringApplication.run(LawlietUserRestApplication.class, args);
        System.setProperty("dubbo.application.logger","slf4j");
        new SpringApplicationBuilder(SchoolRestApplication.class)
                .run(args);
    }



}
