package com.yyh.demo.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.yyh.demo.repository.AppUserDAO;
import com.yyh.demo.repository.AppUserRepository;
import com.yyh.demo.service.AppUserService;
import com.yyh.demo.repository.HouseDAO;
import com.yyh.demo.service.HouseService;

@Configuration
public class ServiceConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public HouseService houseService(HouseDAO repository) {
        return new HouseService(repository);
    }
   
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AppUserService appUserService(AppUserDAO repository) {
        return new AppUserService(repository);
    }
    
    /*@Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AppUserService appUserService(AppUserRepository repository) {
        return new AppUserService(repository);
    }*/
    
}
