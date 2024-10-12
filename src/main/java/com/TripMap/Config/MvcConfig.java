package com.TripMap.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.TripMap.Interceptor.MyInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
    
    //注册拦截器
    @Bean
    public MyInterceptor myinterceptor(){
        return new MyInterceptor();
    }


    //添加拦截器到mvc拦截器链
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myinterceptor()).addPathPatterns("/*");
   }
}
