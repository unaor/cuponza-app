package com.datasol.cuponza.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.datasol.cuponza.controller.validator.UserValidator;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.exception.UserAlreadyExistsException;
import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.UserService;
import com.datasol.cuponza.util.ControllerUtil;
import com.google.gson.Gson;

@Controller
public class UserController extends CuponzaController {
	
	@Autowired
	UserService userService;
	
	@InitBinder("userValidator")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
	}
	
	@RequestMapping(value="/user/add",method = RequestMethod.POST)
	public @ResponseBody String addUser(WebRequest request, @Valid
			@ModelAttribute User user, BindingResult result,Model model,Locale locale){
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
		} catch (UserAlreadyExistsException e) {
			return gson.toJson(messageSource.getMessage("controller.response.user.exists",null, locale));
		}
		
	}
	
	@RequestMapping(value="/user/activate" , method=RequestMethod.GET)
	public @ResponseBody String activateUser(WebRequest request,@RequestParam(value="uuid") String uuid,@RequestParam(value="email") String email){
		Gson gson = new Gson();
		try {
			userService.activateUser(email, uuid);
			return gson.toJson("ok");
		} catch (ServiceException e) {
			return gson.toJson("bad");
		}
	}

}
