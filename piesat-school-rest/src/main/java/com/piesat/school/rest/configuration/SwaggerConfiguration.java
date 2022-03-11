package com.piesat.school.rest.configuration;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {
    @Autowired
    private Environment environment;
    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;
    //@Profile({"dev","test"})
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Piesat school业务文档")
                .description("服务于PIE+，业务接口以PieGateway方式访问")
                .termsOfServiceUrl("").version("1.0")
                .build();
    }

    @Bean
    //@Profile({"dev","test"})
    public Docket defaultApi() {
        // 设置显示的swagger环境信息
        Profiles profiles = Profiles.of("dev", "local", "test");
        // 判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        String groupName = "网页端";
        return new Docket(DocumentationType.SWAGGER_2)
                //.pathMapping("/")
                .groupName(groupName)
                .enable(flag)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.piesat.school.rest.controller.app"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalParameters())
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
    }


    public List<Parameter> globalParameters(){
        List<Parameter> globalParameters = new ArrayList<>();
        globalParameters.add(new ParameterBuilder().
                name("Set-Cookie").
                description("模拟Gateway鉴权后的参数").
                defaultValue("{\"biz\":{\"userId\":\"RXDlb6ORuWUWvsq6eZ2hZ\"}}").
                modelRef(new ModelRef("string")).
                parameterType("header").
                required(false).
                build());
        return globalParameters;
    }

    /*@Profile({"dev","local","test"})
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Piesat school 业务文档")
                .description(
                        //"开发区定义:" +
                        //"\n 1-潮水镇 2-大季家 3-福莱山 4-古现" +
                        //"\n 开发环境接口地址前缀：https://apidevplus.piesat.cn/yt_antiepidemic_api/v1/" +
                        "\n Post方式 Header参数 Content-Type：application/json" +
                        "\n 业务通用错误码（6位）：" +
                        "\n 100098 - 通用接口参数验证错误" +
                        "\n 100099 - 服务器内部错误"
                )
                .termsOfServiceUrl("").version("1.0")
                .build();
    }
    @Bean
    @Profile({"dev","local","test"})
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.piesat.school.rest"))
                .paths(PathSelectors.any())
                .build();
    }*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /*registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");*/

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
