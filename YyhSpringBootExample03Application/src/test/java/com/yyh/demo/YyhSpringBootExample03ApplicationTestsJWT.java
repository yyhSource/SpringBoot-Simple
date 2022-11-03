package com.yyh.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.yyh.demo.entity.House;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class YyhSpringBootExample03ApplicationTestsJWT  extends BaseJWT {

	@Autowired
    private MockMvc mockMvc;	
	
	//-----------//
	//   House   //
	//-----------//
	@Test
	@Order(1)
	public void testGetHouse() throws Exception{
		try {
			
			login("howard@gmail.com", "1234");
		
			RequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/jwt/house/A0001").headers(httpHeaders);
        
			mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").hasJsonPath())
			.andExpect(jsonPath("$.id").value("A0001"))
			.andExpect(jsonPath("$.ownerName").value("Howard"))
			.andExpect(jsonPath("$.type").value("Semi-detached"))
			.andExpect(jsonPath("$.address1").value("8 Bury"))
			.andExpect(jsonPath("$.address2").value("Manchester"))
			.andExpect(jsonPath("$.postCode").value("BL9 H0"))
			.andExpect(jsonPath("$.price").value(284000))
			;
        		
		} catch (Exception e) {
			System.out.println(" testGetHouse Fail : " + e.getMessage());
		}
	}

	@Test
	@Order(3)
	public void testCreateHouse() throws Exception{
		
		try {
			login("howard@gmail.com", "1234");
			
			House house = createHouse("A0005", "yys Jerry", "Semi-detached", "16 Bury", "Manchester", "BL8 2XX", 300008.0);
		
			JSONObject request = new JSONObject();
			request.put("id", house.getId());
			request.put("ownerName", house.getOwnerName());
			request.put("type", house.getType());
			request.put("address1", house.getAddress1());
			request.put("address2", house.getAddress2());
			request.put("postCode", house.getPostCode());
			request.put("price", house.getPrice());
		
			RequestBuilder requestBuilder =  MockMvcRequestBuilders.post("/jwt/house").headers(httpHeaders).content(request.toString());
		
			mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").hasJsonPath())
			.andExpect(jsonPath("$.id").value(house.getId()))
			.andExpect(jsonPath("$.ownerName").value(house.getOwnerName()))
			.andExpect(jsonPath("$.type").value(house.getType()))
			.andExpect(jsonPath("$.address1").value(house.getAddress1()))
			.andExpect(jsonPath("$.address2").value(house.getAddress2()))
			.andExpect(jsonPath("$.postCode").value(house.getPostCode()))
			.andExpect(jsonPath("$.price").value(house.getPrice()))
			;
		} catch (Exception e) {
			System.out.println(" testCreateHouse Fail : " + e.getMessage());
		}			
	}
	
	@Test
	@Order(4)
	public void testUpdateHouse() throws Exception{
	
		try {
			login("howard@gmail.com", "1234");
			
			House house = createHouse("A0001","Howard Yee",  "Semi-detached", "8 Bury",       "Manchester","BL9 H0", 34000.0);

			JSONObject request = new JSONObject();
			request.put("id", house.getId());
			request.put("ownerName", house.getOwnerName());
			request.put("type", house.getType());
			request.put("address1", house.getAddress1());
			request.put("address2", house.getAddress2());
			request.put("postCode", house.getPostCode());
			request.put("price", house.getPrice());
		
			RequestBuilder requestBuilder =  MockMvcRequestBuilders.put("/jwt/house/" + house.getId()).headers(httpHeaders).content(request.toString());
			
			mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").hasJsonPath())
			.andExpect(jsonPath("$.id").value(house.getId()))
			.andExpect(jsonPath("$.ownerName").value(house.getOwnerName()))
			.andExpect(jsonPath("$.type").value(house.getType()))
			.andExpect(jsonPath("$.address1").value(house.getAddress1()))
			.andExpect(jsonPath("$.address2").value(house.getAddress2()))
			.andExpect(jsonPath("$.postCode").value(house.getPostCode()))
			.andExpect(jsonPath("$.price").value(house.getPrice()))
			;
			
		}catch (Exception e) {
			System.out.println(" testUpdateHouse Fail : " + e.getMessage());
		}
	} 
	
	
	@Test
	@Order(5)
	public void testDeleteHouse() throws Exception{
		try {
			login("howard@gmail.com", "1234");
	        mockMvc.perform(delete("/jwt/house/A0001")
	                .headers(httpHeaders))
	        		.andDo(print())
	                .andExpect(status().isNoContent());
		
		}catch (Exception e) {
			System.out.println(" testDeleteHouse Fail : " + e.getMessage());
		}
	}
	
	
	
    private House createHouse(String id, String ownerName, String type, String address1, String address2, String postCode, Double price) {
    	House house = new House();
        
    	house.setId(id);
    	house.setOwnerName(ownerName);
    	house.setType(type);
    	house.setAddress1(address1);
    	house.setAddress2(address2);
    	house.setPostCode(postCode);
    	house.setPrice(price);
    	
        return house;
    }	
	
	
	
}
