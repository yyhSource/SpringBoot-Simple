package com.yyh.demo.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.util.FileCopyUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LogApiFilter extends OncePerRequestFilter  {

	
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
    	
    	System.out.println("LogApiFilter.doFilterInternal");
    	//FileCopyUtils.copyToByteArray(request.getInputStream());
    	
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        chain.doFilter(requestWrapper, responseWrapper);
        
        //chain.doFilter(request, responseWrapper);
    	
    	//Log API
        int httpStatus = response.getStatus();
        String httpMethod = request.getMethod();
        String uri = request.getRequestURI();
        String params = request.getQueryString();

        if (params != null) {
            uri += "?" + params;
        }
        System.out.println("httpStatus=" + String.valueOf(httpStatus) + ",httpMethod=" + httpMethod + ",URI=" + uri);
        System.out.println("Request Content:" + new String(requestWrapper.getContentAsByteArray()).replaceAll("[\n\t]", ""));
        System.out.println("Response Content:" + new String(responseWrapper.getContentAsByteArray()).replaceAll("[\n\t]", ""));
        
        responseWrapper.copyBodyToResponse();
    }
	
}
