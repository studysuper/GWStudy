package com.bootdo.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletContext;

public class DefaultWebAppConfigurer extends WebMvcConfigurationSupport {
    /**
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/** ").addResourceLocations("classpath:/**");
        super.addResourceHandlers(registry);*/
    }
}
