package com.robin.nass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebConfig
 * @Description 开启跨域访问
 * @Author Robin
 * @Date 2022/11/23 20:51
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //匹配所有映射
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT","DELETE");
    }
}
