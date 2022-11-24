package com.robin.nass.config.page;

import com.robin.nass.common.JacksonObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/24 17:55
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //消息转换器
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //对象转换器：使用jackson将java对象转化为java对象
        messageConverter.setObjectMapper(new JacksonObjectMapper());

        //添加到spring的转换器集合中
        converters.add(0,messageConverter);
    }
}
