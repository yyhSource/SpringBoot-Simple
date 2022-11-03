package com.yyh.demo.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yyh.demo.entity.app_user.AppUser;
import com.yyh.demo.exception.NotFoundException;
import com.yyh.demo.service.AppUserService;

@Service
public class SpringUserService  implements UserDetailsService {

    @Autowired
    private AppUserService appUserService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AppUser appUser = appUserService.getUserByEmail(username);
            return new SpringUser(appUser);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Username is wrong.");
        }
    }    
}
