package com.pclogo.demo.configuration;

import com.pclogo.demo.Interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public SessionInterceptor sessionInterceptor(){
        return new SessionInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/loginByName")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/loginByPhone")
                .excludePathPatterns("/user/jiahaoyou")
                .excludePathPatterns("/match/createRoom")
                .excludePathPatterns("/match/joinById")
                .excludePathPatterns("/match/joinSrand")
                .excludePathPatterns("/match/sendCommand")
                .excludePathPatterns("/match/getCommand")
                .excludePathPatterns("/match/getOtherPlayer")
        .excludePathPatterns("/user/searchByName")
        .excludePathPatterns("/user/searchByPhone")
        .excludePathPatterns("/user/acceptInvite")
        .excludePathPatterns("/user/sendInvite")
        .excludePathPatterns("/user/checkInvite")
        .excludePathPatterns("/transform")
        .excludePathPatterns("/match/removeRoom")
        ;
    }
}
