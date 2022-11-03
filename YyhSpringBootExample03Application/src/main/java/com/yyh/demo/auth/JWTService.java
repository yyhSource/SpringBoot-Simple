package com.yyh.demo.auth;

import java.security.Key;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    private final String SECRET_KEY = "YYH_SPRING_BOOT_JWT_AUTH_TEST_DEMO";
                                       
    
    private final Integer TOKEN_EXPIRE_TIME_IN_MINUTE = 10;
    
    public String generateToken(AuthRequest request) {
    	
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, TOKEN_EXPIRE_TIME_IN_MINUTE);

        Claims claims = Jwts.claims();
        claims.put("username", userDetails.getUsername());
        claims.setExpiration(calendar.getTime());
        claims.setIssuer("Test User");

        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }
    
    public Map<String, Object> parseToken(String token) {

   		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
       
   		JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();

   		Claims claims = parser.parseClaimsJws(token).getBody();

   		return claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    		
    }    
    
    
	
}
