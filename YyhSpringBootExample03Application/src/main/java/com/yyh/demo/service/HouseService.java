package com.yyh.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyh.demo.converter.AppUserConverter;
import com.yyh.demo.entity.House;
import com.yyh.demo.entity.app_user.AppUser;
import com.yyh.demo.entity.app_user.AppUserResponse;
import com.yyh.demo.exception.ConflictException;
import com.yyh.demo.exception.NotFoundException;
import com.yyh.demo.repository.HouseDAO;

//@Service
public class HouseService {
	
    //@Autowired
    private HouseDAO houseDAO;
	
    public HouseService(HouseDAO houseDAO) {
    	this.houseDAO = houseDAO;
    }
    
    
    public House getHouse(String id) {
        return houseDAO.find(id).orElseThrow(() -> new NotFoundException("Can't find House."));
    }
	
	
    public House createHouse(House request) {
        boolean isIdDuplicated = houseDAO.find(request.getId()).isPresent();
        if (isIdDuplicated) {
            throw new ConflictException("The id of the house is duplicated.");
        }

        House house = new House();
        house.setId(request.getId());
        house.setOwnerName(request.getOwnerName());
        house.setType(request.getType());
        house.setAddress1(request.getAddress1());
        house.setAddress2(request.getAddress2());
        house.setPostCode(request.getPostCode());
        house.setPrice(request.getPrice());

        return houseDAO.create(house);
    }

    public House replaceHouse(String id, House request) {
    	House house = getHouse(id);
    	
    	House houseReturn = houseDAO.update(house.getId(), request); 
        return houseReturn;
    }
    
    
    public boolean deleteHouse(String id) {
        House house = getHouse(id);
        return houseDAO.delete(house.getId());
    }
    
    public List<House> getAll() {
        return houseDAO.getAll();
    }
    
    
}
