package com.datasol.cuponza.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.datasol.cuponza.controller.validator.UserValidator;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.UserService;
import com.datasol.cuponza.util.ControllerUtil;
import com.google.gson.Gson;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	
	@InitBinder("userValidator")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleException(Exception ex,Locale locale) {	
		//TODO:remove this line for production
		System.out.println("ERROR ERROR ERROR "+ex);
		Gson gson = new Gson();
		return gson.toJson(messageSource.getMessage("controller.response.failure",null, locale)); 
		    }
	
	@RequestMapping(value="/user/add",method = RequestMethod.POST)
	public @ResponseBody String addUser(WebRequest request, @Valid
			@ModelAttribute("user") User user, BindingResult result,Model model,Locale locale){
		Gson gson = new Gson();
		UserValidator validator = new UserValidator();
		validator.validate(user, result);
		model.addAttribute("user", user);
		if(result.hasErrors()){
			return ControllerUtil.decodeErrorMessages(result.getAllErrors(),locale,messageSource);
			//TODO: validate if this is a valid json
		}
		try {
			userService.insertUser(user);
			return gson.toJson("ok");
		} catch (ServiceException e) {
			return gson.toJson(messageSource.getMessage("controller.response.failure",null, locale));
		}
		
	}

}
