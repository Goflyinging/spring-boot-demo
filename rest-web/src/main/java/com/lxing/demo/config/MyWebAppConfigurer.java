package com.lxing.demo.config;

import com.lxing.demo.interceptor.MyInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/***
 * Created on 2017/11/1 <br>
 * Description: [spring拦截器配置 ]<br>
 * @author lxing
 * @version 1.0
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

  @Autowired
  MyInterceptor myInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 多个拦截器组成一个拦截器链
    // addPathPatterns 用于添加拦截规则
    // excludePathPatterns 用户排除拦截
    registry.addInterceptor(myInterceptor)
        .excludePathPatterns("/swagger-resources/**")
        .addPathPatterns("/user/**");
    super.addInterceptors(registry);
  }

}
