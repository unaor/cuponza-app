package com.datasol.cuponza.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@Secured(value={Authority.SYS_ADMIN})
public class CustomerController extends CuponzaController {
	
	@Autowired
	CustomerService customerService;
	
	@InitBinder("customerValidator")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CustomerValidator());
	}
	
	@RequestMapping(value="/customer/add",method = RequestMethod.POST)
	public @ResponseBody String addCustomer(WebRequest request, @Valid
			@ModelAttribute("customer") Customer customer, BindingResult result,Model model,Locale locale){
		Gson gson = new Gson();
		CustomerValidator validator = new CustomerValidator();
		validator.validate(customer, result);
		model.addAttribute("customer", customer);
		if(result.hasErrors()){
			return ControllerUtil.decodeErrorMessages(result.getAllErrors(),locale,messageSource);
			//TODO: validate if this is a valid json
		}
		try {
			customerService.addCustomer(customer);
			return gson.toJson("ok");
		} catch (CustomerExistsException e) {
			FieldError error =new FieldError("customer", "contactEmail", "form.customer.already.exists");			
			return ControllerUtil.decodeErrorMessage(error, locale, messageSource);
		}catch (ServiceException e) {
			return gson.toJson(messageSource.getMessage("controller.response.failure",null, locale));
		}
		
	}
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
