package edu.fzu.se.backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("闲置宝")
                    .description("闲置宝后端接口文档")
                        .version("0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("代码写的都队")
                                .url("https://blog.csdn.net/m0_73644347")))
                .externalDocs(new ExternalDocumentation()
                        .description("项目地址")
                        .url("https://github.com/yihzh-cfdg/IdleTreasure"));
    }
}
