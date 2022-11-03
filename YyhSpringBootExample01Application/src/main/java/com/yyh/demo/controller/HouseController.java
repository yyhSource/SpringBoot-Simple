package com.yyh.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

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

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HouseController {

	private final List<House> houseDBList = new ArrayList<>();
	
    @PostConstruct
    private void initDB() {
    	
    	houseDBList.add(new House("A0001","Howard",  "Semi-detached", "8 Bury",       "Manchester","BL9 H0",284000.0));
    	houseDBList.add(new House("A0002","Harrison","Semi-detached", "6 Stockport",  "Manchester","M23 A12",444000.0));
    	houseDBList.add(new House("A0003","Carson",  "Detached",      "18 Stretford", "Manchester","M20 X76",644000.0));
    	houseDBList.add(new House("A0004","Sean",    "Detached",      "28 Sale",      "Manchester","SA4 E45",644000.0));
    }
    
    @GetMapping("/house/{id}")
    public ResponseEntity<House> getHouse(@PathVariable("id") String id) {
    	
        Optional<House> houseOp = houseDBList.stream().filter(p -> p.getId().equals(id)).findFirst();

        if (!houseOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        House house = houseOp.get();
        return ResponseEntity.ok().body(house);
    }

    @PostMapping("/house")
    public ResponseEntity<House> createProduct(@RequestBody House request) {
        boolean isIdDuplicated = houseDBList.stream()
                .anyMatch(p -> p.getId().equals(request.getId()));
        if (isIdDuplicated) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        House house = new House();
        house.setId(request.getId());
        house.setOwnerName(request.getOwnerName());
        house.setType(request.getType());
        house.setAddress1(request.getAddress1());
        house.setAddress2(request.getAddress2());
        house.setPostCode(request.getPostCode());
        house.setPrice(request.getPrice());
        houseDBList.add(house);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(house.getId())
                .toUri();

        return ResponseEntity.created(location).body(house);
    }
    
    
    @PutMapping("/house/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable("id") String id, @RequestBody House request) {
    	
        Optional<House> houseOp = houseDBList.stream().filter(p -> p.getId().equals(id)).findFirst();    	
        if (!houseOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        House house = houseOp.get(); 
        house.setOwnerName(request.getOwnerName());
        house.setType(request.getType());
        house.setAddress1(request.getAddress1());
        house.setAddress2(request.getAddress2());
        house.setPostCode(request.getPostCode());
        house.setPrice(request.getPrice());
        
        return ResponseEntity.ok(house);
    }

    @DeleteMapping("/house/{id}")
    public ResponseEntity deleteHouse(@PathVariable("id") String id) {
    	
    	if (!houseDBList.removeIf(p -> p.getId().equals(id))){
    		return ResponseEntity.notFound().build();
    	}
        
        return ResponseEntity.noContent().build();
    }

    
    
}
