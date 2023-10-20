//package com.metoo.nspm.core.config.cas.filter;
//
//import com.metoo.nspm.core.jwt.interceptor.JwtInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MyInterceptorConfig implements WebMvcConfigurer {
//
//    @Bean
//    public MyInterceptor myInterceptor(){
//        return new MyInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(myInterceptor())
//                .addPathPatterns("/**")// 拦截所有路径
//                .excludePathPatterns(
//                        "/sms/send");// 配置不需要拦截的路径
//    }
//}
