package com.datasol.cuponza.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
	@RequestMapping(value="/",method = RequestMethod.GET)
	public @ResponseBody String index(HttpServletRequest request, HttpServletResponse response){

		return "hello";
	}

}
