package com.ayuantalking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 11:39
 * @Version 1.0
 */
@Configuration
public class Webconfig extends WebMvcConfigurerAdapter{

    @Autowired
    MiaoshaUserHandlerMethodArgumentResolver miaoshaUserHandlerMethodArgumentResolver;

    @Autowired
    NeedLoginHandlerInterceptor needLoginHandlerInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(miaoshaUserHandlerMethodArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needLoginHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/login/to_login","/login/do_login","/static/**");
    }
}
