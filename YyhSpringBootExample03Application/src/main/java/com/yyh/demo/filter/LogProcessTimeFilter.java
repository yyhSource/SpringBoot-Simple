package com.yyh.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class LogProcessTimeFilter extends OncePerRequestFilter {

	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

    	System.out.println("LogProcessTimeFilter.doFilterInternal");
    	long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long processTime = System.currentTimeMillis() - startTime;
        System.out.println(processTime + " ms");
    }
}
