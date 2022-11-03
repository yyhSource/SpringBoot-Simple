package com.yyh.demo.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yyh.demo.entity.app_user.AppUser;
import com.yyh.demo.entity.app_user.UserAuthority;

public class SpringUser implements UserDetails {
    private AppUser appUser;

    public SpringUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getId() {
        return appUser.getId();
    }

    public String getName() {
        return appUser.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
    	/*Collection<? extends GrantedAuthority> ga = appUser.getAuthorities().stream()
        .map(auth -> new SimpleGrantedAuthority(auth.name()))
        .collect(Collectors.toList());*/
    	//Collection<UserAuthority> ga0 = c.collect(Collectors.toList());
    	
    	Stream<UserAuthority> c = (Stream<UserAuthority>)appUser.getAuthorities().stream();
    	Stream rr = c.map(auth -> new SimpleGrantedAuthority(auth.name()));
    	Collection<? extends GrantedAuthority> ga = (Collection<? extends GrantedAuthority>) rr.collect(Collectors.toList());
    	
        return ga;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

