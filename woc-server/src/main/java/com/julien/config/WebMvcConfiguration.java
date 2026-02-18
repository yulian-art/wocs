package com.julien.config;


import com.julien.Interceptor.JwtTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/competitions")

                .excludePathPatterns("/login");
    }
}
