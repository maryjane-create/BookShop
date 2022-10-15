package com.example.bookstore.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean<ActuatorFilter>registrationBean(){
        FilterRegistrationBean<ActuatorFilter> registrationBean= new FilterRegistrationBean<>();
        registrationBean.setFilter(new ActuatorFilter());
        registrationBean.addUrlPatterns("/actuator");
        return registrationBean;
    }
}
