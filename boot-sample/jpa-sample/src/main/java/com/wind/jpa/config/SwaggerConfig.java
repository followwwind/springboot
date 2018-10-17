package com.wind.jpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Title: SwaggerConfig
 * @Package com.wind.sample.swagger
 * @Description: 在生产环境不开启
 * @author huanghy
 * @date 2018/9/21 14:08
 * @version V1.0
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@Profile({"dev", "local", "test"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wind"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-boot利用swagger构建api文档")
                .description("简单优雅的restful风格")
                .termsOfServiceUrl("https://github.com/swagger-api/swagger-ui")
                //创建人
                .contact(new Contact("wind、", "https://github.com/swagger-api/swagger-ui", "18771933975@163.com"))
                .version("1.0")
                .build();
    }
}
