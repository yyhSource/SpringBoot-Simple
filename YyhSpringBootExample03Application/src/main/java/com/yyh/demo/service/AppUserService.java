package com.yyh.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yyh.demo.entity.app_user.AppUserRequest;
import com.yyh.demo.exception.ConflictException;
import com.yyh.demo.converter.AppUserConverter;
import com.yyh.demo.entity.app_user.AppUserResponse;
import com.yyh.demo.entity.app_user.AppUser;
import com.yyh.demo.exception.NotFoundException;
import com.yyh.demo.repository.AppUserDAO;


public class AppUserService {
	
	
	//private AppUserRepository repository;
	private AppUserDAO repository;
	private BCryptPasswordEncoder passwordEncoder;
	
	/*public AppUserService(AppUserRepository repository) {
		this.repository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}*/
	
	public AppUserService( AppUserDAO repository) {
		this.repository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
    public AppUserResponse createUser(AppUserRequest request) {
        Optional<AppUser> existingUser = repository.findByEmailAddress(request.getEmailAddress());
        if (existingUser.isPresent()) {
            throw new ConflictException("This email address has been used.");
        }

        AppUser user = AppUserConverter.toAppUser(request);
        user.setPassword(request.getPassword());
        
        user = repository.insert(user);
        return AppUserConverter.toAppUserResponse(user);
    }
	
	
	
    public AppUserResponse getUserResponseById(String id) {
        AppUser user = repository.find(id).orElseThrow(() -> new NotFoundException("Can't find user."));
        return AppUserConverter.toAppUserResponse(user);
    }	
	
    public AppUser getUserByEmail(String email) {
        return repository.findByEmailAddress(email).orElseThrow(() -> new NotFoundException("Can't find user."));
    }	

    public List<AppUserResponse> getUserResponses() {
        List<AppUser> users = repository.findAll();
        return AppUserConverter.toAppUserResponses(users);
    }
    
    
    
	    
	    
}
