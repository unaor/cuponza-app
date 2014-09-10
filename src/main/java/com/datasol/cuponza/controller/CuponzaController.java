package com.datasol.cuponza.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;

@Controller
public class CuponzaController {
	
	@Autowired
	MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleException(Exception ex,Locale locale) {	
		//TODO:remove this line for production
		System.out.println("ERROR ERROR ERROR "+ex);
		Gson gson = new Gson();
		return gson.toJson(messageSource.getMessage("controller.response.failure",null, locale)); 
		    }
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public String handleUnauthorizedException(Exception ex,Locale locale) {	
		//TODO:remove this line for production
		System.out.println("ERROR ERROR ERROR "+ex);
		Gson gson = new Gson();
		return gson.toJson(messageSource.getMessage("controller.response.404" + ex,null, locale)); 
		    }
}
