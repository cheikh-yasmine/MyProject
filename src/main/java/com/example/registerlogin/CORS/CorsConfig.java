package com.example.registerlogin.CORS;

import jakarta.servlet.Filter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class CorsConfig {
    @Bean
    public FilterRegistrationBean<Filter> corsFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1); // set precedence
        return registrationBean;
    }
}
