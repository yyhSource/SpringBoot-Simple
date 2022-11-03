package com.yyh.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyh.demo.auth.AuthRequest;

@AutoConfigureMockMvc
@SpringBootTest
public class BaseJWT {

	
	@Autowired
	protected MockMvc mockMvc;
	
	
	protected HttpHeaders httpHeaders;
	protected final ObjectMapper mapper = new ObjectMapper();
	
    @BeforeEach
    public void initHttpHeader() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
	
    protected void login(String emailAddress, String password) throws Exception {
        AuthRequest authReq = new AuthRequest();
        authReq.setUsername(emailAddress);
        authReq.setPassword(password);
        MvcResult result = mockMvc.perform(post("/jwt/auth")
                .headers(httpHeaders)
                .content(mapper.writeValueAsString(authReq)))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject tokenRes = new JSONObject(result.getResponse().getContentAsString());
        String accessToken = tokenRes.getString("token");
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    }

    protected void logout() {
        httpHeaders.remove(HttpHeaders.AUTHORIZATION);
    }
	
}
