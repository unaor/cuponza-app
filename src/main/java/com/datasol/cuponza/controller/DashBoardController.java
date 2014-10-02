package com.datasol.cuponza.controller;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Authority;
import com.datasol.cuponza.model.Customer;
import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.CustomerService;

@Controller
@Secured(value={Authority.NORMAL_USER_ROLE})
public class DashBoardController extends CuponzaController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public String showUserDashboard(Model model,Locale locale,Principal principal){
		User user = (User)principal;
		Customer customer=null;
		String msg=null;
		try {
			customer = customerService.getCustomerByEmail(user.getEmail());
		} catch (ServiceException e) {
			msg = messageSource.getMessage("controller.response.failure",null, locale);
		}
		model.addAttribute("customer", customer);
		model.addAttribute("msg", msg);
		return "customer/dashboard/dashboard";
	}

}
