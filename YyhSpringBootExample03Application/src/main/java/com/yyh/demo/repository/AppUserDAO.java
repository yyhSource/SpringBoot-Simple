package com.yyh.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.yyh.demo.entity.House;
import com.yyh.demo.entity.app_user.AppUser;
import com.yyh.demo.entity.app_user.UserAuthority;

@Repository
public class AppUserDAO {

	private final List<AppUser> appUserDBList = new ArrayList<>();
	
	@PostConstruct
	private void initDB() {

		List<UserAuthority> listUserAuthority;
		
		listUserAuthority = new ArrayList<UserAuthority>();
		listUserAuthority.add(UserAuthority.ADMIN);
		listUserAuthority.add(UserAuthority.NORMAL);
	    appUserDBList.add(new AppUser("U0001","howard@gmail.com",  "1234" , "Howard", listUserAuthority));

		listUserAuthority = new ArrayList<UserAuthority>();
		listUserAuthority.add(UserAuthority.ADMIN);
	    appUserDBList.add(new AppUser("U0002","harrison@gmail.com","4321",  "Harrison", listUserAuthority));
	    
		listUserAuthority = new ArrayList<UserAuthority>();
		listUserAuthority.add(UserAuthority.NORMAL);	    
	    appUserDBList.add(new AppUser("U0003","carson@gmail.com",  "5678",  "Carson",   listUserAuthority));
	    
		listUserAuthority = new ArrayList<UserAuthority>();
		listUserAuthority.add(UserAuthority.NORMAL);	    
	    appUserDBList.add(new AppUser("U0004","sean@gmail.com",    "8765",  "Sean",    listUserAuthority));
	}

    public Optional<AppUser> find(String id) {
    	Optional<AppUser> appUserOp = appUserDBList.stream().filter(p -> p.getId().equals(id)).findFirst();
        return appUserOp;
    }
    
    public List<AppUser> findAll() {
        return appUserDBList;
    }    
    
    public Optional<AppUser> findByEmailAddress(String email) {
    	Optional<AppUser> appUserOp = appUserDBList.stream().filter(p -> p.getEmailAddress().equals(email)).findFirst();
        return appUserOp;
    }    
    
    public AppUser insert(AppUser user) {
    	appUserDBList.add(user);
    	return user;
    	
    }
    
}
