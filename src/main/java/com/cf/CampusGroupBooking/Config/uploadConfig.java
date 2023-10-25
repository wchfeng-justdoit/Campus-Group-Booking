package com.cf.CampusGroupBooking.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class uploadConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/head/**").addResourceLocations("file:E:\\JAVA\\pintuan/head/");
        registry.addResourceHandler("/identify/**").addResourceLocations("file:E:\\JAVA\\pintuan/identify/");
    }
}
