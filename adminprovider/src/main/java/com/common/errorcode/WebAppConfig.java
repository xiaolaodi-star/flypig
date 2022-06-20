package com.common.errorcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web应用程序配置
 *
 * @author littledotboy
 * @date 2022/06/13
 * @see WebMvcConfigurationSupport
 */
@Slf4j
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
    /**
     * {方法注释}添加拦截器
     *
     * @param registry 注册表
     * @author littledotboy
     * @date 2022/04/26
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        注册拦截器
        log.info("********注册********");
        registry.addInterceptor(new ResponseTranslateHandle()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
