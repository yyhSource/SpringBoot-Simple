package com.yyh.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.yyh.demo.entity.app_user.AppUser;

@Repository
public interface AppUserRepository {

    Optional<AppUser> findByEmailAddress(String email);
	
}
