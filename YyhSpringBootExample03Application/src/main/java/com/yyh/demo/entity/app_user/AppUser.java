package com.yyh.demo.entity.app_user;

import java.util.ArrayList;
import java.util.List;

//@Document("users")
public class AppUser {
    private String id;
    private String emailAddress;
    private String password;
    private String name;
    private List<UserAuthority> authorities;

    public AppUser() {
    	List<UserAuthority> authorities = new ArrayList<UserAuthority>();
    }
    
    public AppUser(String id, String emailAddress, String password, String name, List<UserAuthority> authorities) {
    	this.id = id;
    	this.emailAddress = emailAddress;
    	this.password = password;
    	this.name = name;
    	this.authorities = authorities;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<UserAuthority> authorities) {
        this.authorities = authorities;
    }
}

