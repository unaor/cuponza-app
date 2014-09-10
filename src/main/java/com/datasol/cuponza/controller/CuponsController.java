package com.datasol.cuponza.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Cupon;
import com.datasol.cuponza.service.CuponService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CuponsController extends CuponzaController {

	@Autowired
	CuponService cuponService;

	@RequestMapping(value = "/cupons/byLocation", method = RequestMethod.POST)
	public @ResponseBody String getCuponsByLocation(WebRequest request ,@RequestParam Float longitude, @RequestParam Float latitude, Locale locale) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		List<Cupon> cupons = null;
		try {
			cupons = cuponService.getCuponsByLocation(longitude, latitude);
		} catch (ServiceException e) {
			return gson.toJson(messageSource.getMessage(
					"controller.response.failure", null, locale));
		}
		return gson.toJson(cupons);
	}

}
