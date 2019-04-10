package com.ayuantalking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 23:29
 * @Version 1.0
 */
@Configurable
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    MiaoshaUserArgumentResolvers miaoshaUserArgumentResolvers;

    @Autowired
    NeedLoginHandlerInterceptor needLoginHandlerInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(miaoshaUserArgumentResolvers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needLoginHandlerInterceptor);
    }


}
