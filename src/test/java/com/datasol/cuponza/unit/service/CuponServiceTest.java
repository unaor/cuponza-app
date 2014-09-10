package com.datasol.cuponza.unit.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Business;
import com.datasol.cuponza.model.Cupon;
import com.datasol.cuponza.service.CuponService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class CuponServiceTest {
	
	@Autowired
	CuponService cuponService;
	
	@Test
	public void getCouponsByLocation() throws ServiceException{
		 Float longitude = -6.7675f;
		 Float latitude   = 75.554f;
		 List<Cupon> cupons  =cuponService.getCuponsByLocation(longitude, latitude);
		 assertEquals(2,cupons.size());
		 Business business = cupons.get(0).getBusiness();
		 assertEquals("Santa fe", business.getBusinessName());
		 
		 Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		 String test =gson.toJson(cupons);
		 System.out.println(test);
		 assertNotNull(test);
		
	}

}
