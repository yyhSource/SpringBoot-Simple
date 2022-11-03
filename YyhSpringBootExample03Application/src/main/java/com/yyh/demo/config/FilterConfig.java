package com.yyh.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yyh.demo.filter.LogApiFilter;
import com.yyh.demo.filter.LogProcessTimeFilter;

@Configuration
public class FilterConfig {

	
    @Bean
    public FilterRegistrationBean logApiFilter() {
        FilterRegistrationBean<LogApiFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogApiFilter());
        bean.addUrlPatterns("/*");
        bean.setName("logApiFilter");
        bean.setOrder(0);
        System.out.println("FilterConfig.logApiFilter()");
        return bean;
    }

    @Bean
    public FilterRegistrationBean logProcessTimeFilter() {
        FilterRegistrationBean<LogProcessTimeFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogProcessTimeFilter());
        bean.addUrlPatterns("/*");
        bean.setName("logProcessTimeFilter");
        bean.setOrder(1);
        System.out.println("FilterConfig.logProcessTimeFilter()");
        return bean;
    }

	
}
