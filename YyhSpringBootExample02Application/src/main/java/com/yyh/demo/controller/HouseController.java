package com.yyh.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.yyh.demo.entity.House;
import com.yyh.demo.exception.NotFoundException;
import com.yyh.demo.service.HouseService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HouseController {

    @Autowired
    private HouseService houseService;
    
    @GetMapping("/house/{id}")
    public ResponseEntity<House> getHouse(@PathVariable("id") String id) {
   		House house = houseService.getHouse(id);
   		if (house == null) {
   			return ResponseEntity.notFound().build();
   		}
   		return ResponseEntity.ok().body(house);
    }

    @PostMapping("/house")
    public ResponseEntity<House> createHouse(@RequestBody House request) {
    	House house = houseService.createHouse(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(house.getId())
                .toUri();

        return ResponseEntity.created(location).body(house);
    }
    
    
    @PutMapping("/house/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable("id") String id, @RequestBody House request) {
    	House house = houseService.replaceHouse(id,request);
        return ResponseEntity.ok(house);
    }

    @DeleteMapping("/house/{id}")
    public ResponseEntity deleteHouse(@PathVariable("id") String id) {
    	houseService.deleteHouse(id);
        return ResponseEntity.noContent().build();
    }

    
    
}
