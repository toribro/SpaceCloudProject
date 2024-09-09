package com.toribro.space.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 외부 경로를 정적 리소스 경로로 매핑
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/img/");
    }
}
