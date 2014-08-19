package com.datasol.cuponza.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
	@RequestMapping(value="/",method = RequestMethod.GET)
	public @ResponseBody String index(HttpServletRequest request, HttpServletResponse response){
//		  HttpHeaders responseHeaders = new HttpHeaders();	   
//		  Enumeration headerNames = request.getHeaderNames();
//		  while(headerNames.hasMoreElements()){
//		   String nextElement = (String)headerNames.nextElement();
//		   System.out.println(nextElement + "=" + request.getHeaders(nextElement));
//		   responseHeaders.set(nextElement, request.getHeader(nextElement));
//		  }
		   
		   
		  //populating the header required for CORS
		 // responseHeaders.set("Access-Control-Allow-Origin", "*"); 
		return "hello";
	}

}
