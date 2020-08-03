package com.chen.myblog.interecpt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webmvcconfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new logininterecpt()).addPathPatterns("/admin/**").excludePathPatterns("/admin").excludePathPatterns("/admin/login").excludePathPatterns("/admin/types").excludePathPatterns("/admin/types/input").excludePathPatterns("admin/blogs");
    }
}
