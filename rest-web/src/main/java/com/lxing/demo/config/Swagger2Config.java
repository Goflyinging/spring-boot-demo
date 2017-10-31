package com.lxing.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 * Created on 2017/11/1 <br>
 * Description: [SwaggerUI配置 ]<br>
 * @author lxing
 * @version 1.0
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        //为当前包路径
        .apis(RequestHandlerSelectors.basePackage("com.lxing.demo.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  //构建 api文档的详细信息函数
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        //页面标题
        .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
        .termsOfServiceUrl("http://localhost/")
        //创建人
        .contact(new Contact("lxing", "", "superlxing@163.com"))
        //版本号
        .version("1.0")
        //描述
        .description("Demo API 描述")
        .build();
  }
}
