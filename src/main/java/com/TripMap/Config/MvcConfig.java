package com.TripMap.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.TripMap.Interceptor.MyInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    @Autowired
    private MyInterceptor myInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")    // 拦截所有请求
                .excludePathPatterns("/static/**");  // 排除静态资源
    }
}
