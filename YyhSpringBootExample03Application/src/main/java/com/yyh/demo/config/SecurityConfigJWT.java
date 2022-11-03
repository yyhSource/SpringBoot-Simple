package com.yyh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yyh.demo.auth.JWTAuthenticationFilter;
import com.yyh.demo.entity.app_user.UserAuthority;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfigJWT extends WebSecurityConfigurerAdapter 
{
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsService userDetailsService;
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
      http
        .antMatcher("/jwt/**")
        .cors()
        .and()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.GET, "/jwt/users").hasAuthority(UserAuthority.ADMIN.name())
        	.antMatchers(HttpMethod.GET, "/jwt/users/*").authenticated()
          	.antMatchers(HttpMethod.GET, "/jwt/house").hasAuthority(UserAuthority.ADMIN.name())
          	.antMatchers(HttpMethod.GET, "/jwt/house/*").authenticated()
          	.antMatchers(HttpMethod.POST, "/jwt/house").hasAuthority(UserAuthority.ADMIN.name())
          	.antMatchers(HttpMethod.PUT, "/jwt/house/*").hasAuthority(UserAuthority.ADMIN.name())
          	.antMatchers(HttpMethod.DELETE, "/jwt/house/*").hasAuthority(UserAuthority.ADMIN.name())
            .antMatchers(HttpMethod.POST, "/jwt/auth").permitAll()    
            .antMatchers(HttpMethod.POST, "/jwt/auth/parse").permitAll()
            //.anyRequest().authenticated()
            .anyRequest().denyAll()
        .and()
        	.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        	.csrf().disable()
        .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
