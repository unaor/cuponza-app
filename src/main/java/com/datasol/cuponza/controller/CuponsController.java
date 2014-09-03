package com.datasol.cuponza.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datasol.cuponza.model.Coordinate;

public class CuponsController extends CuponzaController{
	

	@RequestMapping(value="/cupons/byLocation",method = RequestMethod.GET)
	@ResponseBody
	public String getCuponsByLocation(@RequestBody Coordinate coordinates){
		return "";
	}

}
