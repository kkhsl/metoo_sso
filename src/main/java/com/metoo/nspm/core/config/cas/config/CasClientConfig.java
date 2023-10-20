package com.metoo.nspm.core.config.cas.config;

import com.metoo.nspm.core.config.cas.filter.AuthenticationFilter;
import com.metoo.nspm.core.config.cas.filter.ClientCas20ProxyReceivingTicketValidationFilter;
import com.metoo.nspm.core.service.ILoginLogService;
import net.unicon.cas.client.configuration.CasClientConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.HashMap;
import java.util.Map;

// 自定义重定向策略
@Configuration
public class CasClientConfig extends CasClientConfigurerAdapter {

    @Autowired
    private ILoginLogService loginLogService;

    @Override
    public void configureAuthenticationFilter(FilterRegistrationBean authenticationFilter) {
        Map<String, String> initParameters = authenticationFilter.getInitParameters();
        initParameters.put("authenticationRedirectStrategyClass",
                "com.metoo.nspm.core.config.cas.strategy.ClientAuthRedirectStrategy");
    }

    @Override
    public void configureValidationFilter(FilterRegistrationBean validationFilter) {

        Map<String, String> initParameters = validationFilter.getInitParameters();
        initParameters.put("encodeServiceUrl", "false");

        // 票据校验过滤器
        validationFilter.setFilter(new ClientCas20ProxyReceivingTicketValidationFilter(loginLogService));
    }

//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CorsFilter(source));
//        registrationBean.setOrder(-2147483648);
//        return registrationBean;
//    }


//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//
//        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new CorsFilter(source));
//
////        registrationBean.setOrder(-2147483648);
//
//
//
//        // 设定匹配的路径
//        registrationBean.addUrlPatterns("/*");
//
//        Map<String,String>  initParameters = new HashMap<>();
////        initParameters.put("casServerLoginUrl", login);
////        initParameters.put("serverName", client);
//        //设置忽略  退出登录不用登录CAS
//        initParameters.put("ignorePattern", "/admin/sendmsg");
//        registrationBean.setInitParameters(initParameters);
//        // 设定加载的顺序
//        registrationBean.setOrder(1);
//
//
//        return registrationBean;
//    }

    /**
     * 拦截所有请求，将未携带票据与会话中无票据的请求都重定向到CAS登录地址
     */
//    @Bean
//    @Order(1)
//    public FilterRegistrationBean<AuthenticationFilter> casAuthenticationFilter() {
//        FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new AuthenticationFilter());
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("casServerUrlPrefix", "https://java1234.com:8443/cas"); // CAS服务端地址，会拼接为登录地址
//        initParams.put("serverName", "http://java1234.com:8089"); // 服务地址
//        registration.setInitParameters(initParams);
//        registration.addUrlPatterns("/GroupType/*");
//        return registration;
//    }



    /**
     * description:授权过滤器
     * ignoreUrlPatternType 使用CAS现成的正则表达式过滤策略
     */
//    @Bean
//    public FilterRegistrationBean filterAuthenticationRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new AuthenticationFilter());
//        registration.addUrlPatterns("/*");
//
//        Map<String,String> initParameters = new HashMap<String, String>();
//        initParameters.put("casServerLoginUrl", casServerLoginUrl);
//        initParameters.put("serverName", casClientHostUrl);
//        //配置文件中设置要过滤拦截的路径
//        initParameters.put("ignorePattern", casIgnorePattern);
//        initParameters.put("ignoreUrlPatternType", "org.jasig.cas.client.authentication.RegexUrlPatternMatcherStrategy");
//        registration.setInitParameters(initParameters);
//
//        registration.setOrder(1);
//        return registration;
//    }



}
