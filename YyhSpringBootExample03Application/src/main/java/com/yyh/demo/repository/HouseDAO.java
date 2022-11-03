package com.yyh.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.yyh.demo.entity.House;

@Repository
public class HouseDAO {

	private final List<House> houseDBList = new ArrayList<>();
	
    @PostConstruct
    private void initDB() {
    	
    	houseDBList.add(new House("A0001","Howard",  "Semi-detached", "8 Bury",       "Manchester","BL9 H0",284000.0));
    	houseDBList.add(new House("A0002","Harrison","Semi-detached", "6 Stockport",  "Manchester","M23 A12",444000.0));
    	houseDBList.add(new House("A0003","Carson",  "Detached",      "18 Stretford", "Manchester","M20 X76",644000.0));
    	houseDBList.add(new House("A0004","Sean",    "Detached",      "28 Sale",      "Manchester","SA4 E45",644000.0));
    }

    public Optional<House> find(String id) {
    	Optional<House> houseOp = houseDBList.stream().filter(p -> p.getId().equals(id)).findFirst();
        return houseOp;
    }

    public House create(House house) {
    	houseDBList.add(house);
        return house;
    }
    
    public House update(String id, House houseUpd) {
    	
        Optional<House> houseOp = houseDBList.stream().filter(p -> p.getId().equals(id)).findFirst();    	
        if (!houseOp.isPresent()) {
            return null;
        }
        
        House house = houseOp.get();
        house.setOwnerName(houseUpd.getOwnerName());
        house.setType(houseUpd.getType());
        house.setAddress1(houseUpd.getAddress1());
        house.setAddress2(houseUpd.getAddress2());
        house.setPostCode(houseUpd.getPostCode());
        house.setPrice(houseUpd.getPrice());        

        return house;
    }
    
    public boolean delete(String id) {
    	if (!houseDBList.removeIf(p -> p.getId().equals(id))){
    		return false;
    	}
    	return true;
    }
    
    public List<House> getAll(){
    	return houseDBList; 
    }
    
}
