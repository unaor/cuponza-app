package com.datasol.cuponza.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.datasol.cuponza.controller.validator.CustomerValidator;
import com.datasol.cuponza.exception.CustomerExistsException;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Authority;
import com.datasol.cuponza.model.Customer;
import com.datasol.cuponza.service.CustomerService;
import com.datasol.cuponza.util.ControllerUtil;
import com.datasol.cuponza.util.ErrorMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CustomerController extends CuponzaController {
	
	@Autowired
	CustomerService customerService;
	
	@InitBinder("customerValidator")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CustomerValidator());
	}
	
	@RequestMapping(value="/customer/add",method = RequestMethod.GET)
	public String addCustomer(){
		return "customer/customerAdd";
	}
	
	@RequestMapping(value="/customer/add",method = RequestMethod.POST)
	public @ResponseBody String addCustomer(WebRequest request, @Valid
			@ModelAttribute Customer customer, BindingResult result,Model model,Locale locale){
		Gson gson = new Gson();
		CustomerValidator validator = new CustomerValidator();
		validator.validate(customer, result);
		model.addAttribute("customer", customer);
		if(result.hasErrors()){
			return gson.toJson(ControllerUtil.decodeErrorMessage(result.getAllErrors(),locale,messageSource));
		}
		try {
			String password = customer.getPassword();
			customerService.addCustomer(customer);
			customer.setPassword(password);
			customerService.addUserFromClient(customer);
			return "ok";
		} catch (CustomerExistsException e) {	
			ErrorMessage error = new ErrorMessage();
			error.setErrorHeader("contactEmail");
			String message  = messageSource.getMessage("form.customer.already.exists",null, locale);
			error.setErrorDescription(message);
			List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
			errorMessages.add(error);
			return gson.toJson(errorMessages);
		}catch (ServiceException e) {
			return gson.toJson(messageSource.getMessage("controller.response.failure",null, locale));
		}
		
	}
	@Secured(value={Authority.SYS_ADMIN})
	@RequestMapping(value="/customer/all",method = RequestMethod.GET)
	public @ResponseBody String getAllCustomers(WebRequest request,Locale locale){
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		try {
			return gson.toJson(customerService.getAllCustomers());
		} catch (ServiceException e) {
			return gson.toJson(messageSource.getMessage("controller.response.failure",null, locale));
		}
	}

}
