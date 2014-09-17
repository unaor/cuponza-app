package com.datasol.cuponza.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CuponzaController {
	
	@Autowired
	MessageSource messageSource;
	
	private  String accessDeniedUrl="/cuponza/dist/error-pages/403.jsp";
	private  String internalServerErrorUrl="/cuponza/dist/error-pages/500.jsp";
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public void handleException(Exception ex,Locale locale,HttpServletResponse response,HttpServletRequest request) throws IOException {	
		
		//TODO:remove this line for production
		System.out.println("ERROR ERROR ERROR "+ex);
		response.sendRedirect(internalServerErrorUrl);  
	       request.getSession().setAttribute("msg",  
	        ex);   
		    }
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public void handleUnauthorizedException(Exception ex,Locale locale,HttpServletResponse response,HttpServletRequest request) throws IOException {	
		//TODO:remove this line for production
		System.out.println("ERROR ERROR ERROR "+ex);
		response.sendRedirect(accessDeniedUrl);  
	       request.getSession().setAttribute("msg",  
	        "Zona restrengida tienes que logear");  
		    }
}
