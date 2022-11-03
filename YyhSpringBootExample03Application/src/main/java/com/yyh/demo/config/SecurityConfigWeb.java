package com.yyh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


import com.yyh.demo.auth.JWTAuthenticationFilter;
import com.yyh.demo.entity.app_user.UserAuthority;



@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfigWeb  extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService userDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	  http
    	  .antMatcher("/**")
          .cors()
    	  .and()
          .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/users").hasAuthority(UserAuthority.ADMIN.name())
          	.antMatchers(HttpMethod.GET, "/users/**").authenticated()
          	.antMatchers(HttpMethod.GET, "/house").hasAuthority(UserAuthority.ADMIN.name())
          	.antMatchers(HttpMethod.GET, "/house/*").authenticated()
          	.antMatchers(HttpMethod.POST, "/house").hasAuthority(UserAuthority.ADMIN.name())
          	.antMatchers(HttpMethod.POST, "/users").authenticated()
            .antMatchers("/login*").permitAll()
          	//.anyRequest().authenticated()
          	.anyRequest().denyAll()
          .and()
          .formLogin()
          	.loginPage("/login")
          		.loginProcessingUrl("/processLogin")
          		.usernameParameter("email")
          		.passwordParameter("password")
          	.defaultSuccessUrl("/login_success")
          	.failureUrl("/login_error")
          	.permitAll()
          .and()
          	.logout().permitAll()
          //.and()
          //.exceptionHandling().accessDeniedPage("/403")
          .and()
          	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
          .and()
          	.csrf().disable();
    }
	
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());    	
    	auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	AuthenticationManager am = super.authenticationManagerBean();
        return am;
    }

}
